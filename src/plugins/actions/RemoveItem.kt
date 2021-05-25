package plugins.actions

import apps.JTreeWindow
import objects.JObject
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.widgets.Shell

class RemoveItem : ActionsPlugin {

    override val name: String
        get() = "Remove"

    override fun execute(jTreeWindow: JTreeWindow) {
        val treeItem = jTreeWindow.tree.selection.first()
        if (treeItem.parentItem != null) {
            (treeItem.parentItem.data as JObject).objects.removeIf { keyValuePair ->
                keyValuePair.value == treeItem.data
            }
            jTreeWindow.refresh()
        }
        else {
            showShell(jTreeWindow.shell)
        }
    }


    private fun showShell(editShell: Shell){
        val tryAgainShell = Shell(editShell)
        tryAgainShell.text = "Error"
        tryAgainShell.layout = GridLayout(1, false)
        val tryAgainLabel = Label(tryAgainShell, SWT.NONE)
        tryAgainLabel.text = "Cannot Remove Root Object!"
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