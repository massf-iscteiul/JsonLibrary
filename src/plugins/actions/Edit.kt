package plugins.actions

import apps.JTreeWindow
import objects.*
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class Edit : ActionsPlugin {

    enum class ErrorType{
        OBJECT, NUMBER
    }

    override val name: String
        get() = "Edit Value"

    override fun execute(jTreeWindow: JTreeWindow) {
        val editShell = Shell(jTreeWindow.shell)
        val treeItem = jTreeWindow.tree.selection.first()

        if (treeItem.data is JObject || treeItem.data is JNull || treeItem.data is JArray){
            showShell(editShell, ErrorType.OBJECT)
        }
        else {
            jTreeWindow.shell.enabled = false
            editShell.text = name
            editShell.layout = GridLayout(1, false)
            val composite = Composite(editShell, 2)
            composite.layout = GridLayout(2, false)

            val label = Label(composite, SWT.WRAP or SWT.SINGLE)
            label.text = treeItem.text.split(":")[0]
            val text = Text(composite, SWT.BORDER)

            val editButton = Button(editShell, SWT.PUSH)
            editButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
            editButton.text = "Edit!"

            editButton.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    when (treeItem.data) {
                        is JString -> {
                            (treeItem.data as JString).value = text.text
                        }
                        is JNumber -> {
                            try {
                                (treeItem.data as JNumber).value = (text.text.toInt())
                            } catch (e: Exception) {
                                showShell(editShell, ErrorType.NUMBER)
                                return
                            }
                        }
                        is JBoolean -> {
                            (treeItem.data as JBoolean).value = (text.text.toBoolean())
                        }
                    }
                    jTreeWindow.jsonLabel.text = treeItem.data.toString()
                    jTreeWindow.refresh()
                    jTreeWindow.shell.enabled = true
                    editShell.close()
                }
            })
            editShell.open()
            editShell.pack()
            val display = Display.getDefault()
            while (!editShell.isDisposed) {
                if (!display.readAndDispatch()) display.sleep()
            }
            jTreeWindow.shell.enabled = true
        }
    }

    private fun showShell(editShell: Shell, type: ErrorType){
        val tryAgainShell = Shell(editShell)
        tryAgainShell.text = "Error"
        tryAgainShell.layout = GridLayout(1, false)
        val tryAgainLabel = Label(tryAgainShell, SWT.NONE)
        if (type == ErrorType.NUMBER) {
            tryAgainLabel.text = "Input must be a number! Try again."
        }else{
            tryAgainLabel.text = "Not an editable object!"
        }
        val okButton = Button(tryAgainShell, SWT.PUSH)
        okButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
        okButton.text = "OK"
        okButton.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                tryAgainShell.close()
            }
        })
        tryAgainShell.open()
        tryAgainShell.pack()
    }

}