package plugins

import JTree
import objects.JBoolean
import objects.JLeaf
import objects.JNumber
import objects.JString
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.ImageData
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import utils.traverse
import java.io.File

class EditPlugin : ActionsPlugin {

    val textBoxs = mutableListOf<Text>()

    override val name: String
        get() = "Edit Value"

    override fun execute(jTree: JTree) {
        // jTree.shell.enabled = false
        val editShell = Shell(jTree.shell)

        editShell.text = name
        editShell.layout = GridLayout(1, false)
        val composite = Composite(editShell, 2)
        composite.layout = GridLayout(2, false)

        val treeItem = jTree.tree.selection.first()
        val label = Label(composite, SWT.WRAP or SWT.SINGLE)

        label.text = treeItem.text
        val text = Text(composite, SWT.BORDER)

        val editButton = Button(editShell, SWT.PUSH)
        editButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
        editButton.text = "Edit!"

        editButton.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                when(treeItem.data){
                    is JString -> {
                        (treeItem.data as JString).value = text.text
                        editShell.close()
                    }
                    is JNumber -> {
                        try {
                            (treeItem.data as JNumber).value = (text.text.toInt())
                            editShell.close()
                        }
                        catch (e: Exception){
                            showTryAgain(editShell)
                        }
                    }
                    is JBoolean -> {
                        (treeItem.data as JBoolean).value = (text.text.toBoolean())
                        editShell.close()
                    }
                }

            }
        })

        /*jTree.tree.selection.first().traverse {
            if (it.data is JString){
                val key = Label(composite, SWT.WRAP or SWT.SINGLE)
                key.text = it.text
                val value = Text(composite, SWT.BORDER)
                value.data = it
                textBoxs.add(value)
            }
        }*/

        editShell.open()
        editShell.pack()

    }

    private fun showTryAgain(writeJsonShell: Shell){
        val tryAgainShell = Shell(writeJsonShell)
        tryAgainShell.text = "Error"
        tryAgainShell.layout = GridLayout(1, false)
        val tryAgainLabel = Label(tryAgainShell, SWT.NONE)
        tryAgainLabel.text = "Input must be a number! Try again."
        val sendButton = Button(tryAgainShell, SWT.PUSH)
        sendButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
        sendButton.text = "OK"
        sendButton.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                tryAgainShell.close()
            }
        })
        tryAgainShell.open()
        tryAgainShell.pack()
    }

}