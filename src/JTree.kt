import objects.JArray
import objects.JObject
import objects.JLeaf
import objects.Visitable
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*
import plugins.ActionsPlugin
import plugins.PresentationPlugin
import utils.expandAll
import utils.traverse
import javax.swing.JButton

class JTree(builder: Visitable) {
    val shell: Shell
    val tree: Tree
    val jsonLabel: Label
    val jsonText: Text

    @Inject
    private lateinit var presentationPlugin: PresentationPlugin

    @Injectadd
    private var actions = mutableListOf<ActionsPlugin>()

    init {
        shell = Shell(Display.getDefault())
        shell.text = "Json Visualizer"
        shell.layout = GridLayout(2, true)
        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)
        tree.layoutData = GridData(GridData.FILL_BOTH)
        jsonLabel = Label(shell, SWT.BORDER or SWT.WRAP or SWT.V_SCROLL or SWT.SINGLE)
        jsonLabel.layoutData = GridData(GridData.FILL_BOTH)

        jsonText = Text(shell, SWT.BORDER)
        jsonText.layoutData = GridData(GridData.FILL_HORIZONTAL)
        jsonText.addModifyListener {
            searchInTree()
        }

        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                jsonLabel.text = tree.selection.first().data.toString()
            }
        })

        buildTree(builder, tree)
    }

    private fun searchInTree(){
        tree.traverse {
            if (jsonText.text != "") {
                it.background = if (it.text.contains(jsonText.text)) Color(114, 188, 212) else null
            } else it.background = null
        }
    }

    private fun buildTree(jValue: Visitable, treeBranch: Any, key: String? = null) {
        val treeItem = if (treeBranch is Tree) {
            TreeItem(treeBranch, SWT.None)
        } else {
            TreeItem(treeBranch as TreeItem, SWT.None)
        }
        when (jValue) {
            is JLeaf -> {
                if (key != null) {
                    treeItem.text = "$key: $jValue"
                } else {
                    treeItem.text = "$jValue"
                }
                treeItem.data = jValue
            }
            is JObject -> {
                if (key != null) {
                    treeItem.text = "$key: (object)"
                } else {
                    treeItem.text = "(object)"
                }
                treeItem.data = jValue
                jValue.objects.forEach {
                    buildTree(it.value, treeItem, it.key)
                }
            }
            is JArray -> {
                treeItem.text = "$key"
                treeItem.data = jValue
                jValue.allJValues.forEach {
                    buildTree(it, treeItem)
                }
            }
        }
    }

    private fun createActionButtons(){
        val jTree = this
        actions.forEach { action ->
            val button = Button(shell, SWT.PUSH)
            button.text = action.name
            button.addSelectionListener(object : SelectionAdapter() {
                override fun widgetSelected(e: SelectionEvent) {
                    action.execute(jTree)
                }
            })
        }
    }

    fun open() {
        tree.expandAll()
        presentationPlugin.execute(this)
        createActionButtons()
        shell.open()
        shell.pack()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()
    }

}