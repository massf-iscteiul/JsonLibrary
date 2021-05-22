package plugins

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
        val newShell = Shell(jTree.shell)
        newShell.text = "Write Json"
        newShell.layout = GridLayout(1, false)
        val composite = Composite(newShell, 3)
        composite.layout = GridLayout(2, false)
        val pathLabel = Label(composite, SWT.WRAP or SWT.SINGLE)
        pathLabel.text = "Choose path:"
        val path = Text(composite, SWT.BORDER)
        val sendButton = Button(newShell, SWT.PUSH)
        sendButton.layoutData = GridData(GridData.FILL_HORIZONTAL)
        sendButton.text = "Write"
        newShell.open()
        newShell.pack()
        sendButton.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val fileName = path.text
                val jsonFile = File(fileName)
                jsonFile.printWriter().use { out ->
                    out.println(jTree.jsonLabel.text)
                }
                newShell.close()
            }
        })
    }

}