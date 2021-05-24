package plugins.actions

import JTree
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import java.io.File

class WriteJson : ActionsPlugin {

    override val name: String
        get() = "Write to Json"

    override fun execute(jTree: JTree) {
        val writeJsonShell = Shell(jTree.shell)
        writeJsonShell.text = name
        writeJsonShell.layout = GridLayout(1, false)
        val composite = Composite(writeJsonShell, 2)
        composite.layout = GridLayout(2, false)
        val pathLabel = Label(composite, SWT.WRAP or SWT.SINGLE)
        pathLabel.text = "Choose path:"
        val path = Text(composite, SWT.BORDER)
        val sendButton = Button(writeJsonShell, SWT.PUSH)
        sendButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
        sendButton.text = "Write"
        writeJsonShell.open()
        writeJsonShell.pack()
        sendButton.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                if (path.text == ""){
                    showTryAgain(writeJsonShell)
                }else {
                    val fileName = path.text
                    val jsonFile = File(fileName)
                    jsonFile.printWriter().use { out ->
                        out.println(jTree.jsonLabel.text)
                    }
                    writeJsonShell.close()
                }
            }
        })
    }

    private fun showTryAgain(writeJsonShell: Shell){
        val tryAgainShell = Shell(writeJsonShell)
        tryAgainShell.text = "Error"
        tryAgainShell.layout = GridLayout(1, false)
        val tryAgainLabel = Label(tryAgainShell, SWT.NONE)
        tryAgainLabel.text = "No path given. Try Again!"
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