import objects.JArray
import objects.JObject
import objects.Leaf
import objects.Visitable
import org.eclipse.swt.SWT
import org.eclipse.swt.events.ModifyEvent
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.graphics.Color
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class JTree(builder: Visitable) {
    val shell: Shell
    val tree: Tree
    val jsonLabel: Label
    val jsonText: Text

    init {
        shell = Shell(Display.getDefault())
        shell.text = "TITLE TITLE"
        shell.layout = GridLayout(2, true)

        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)

        jsonLabel = Label(shell, SWT.BORDER or SWT.WRAP or SWT.V_SCROLL)
        jsonLabel.layoutData = GridData(GridData.FILL_BOTH)

        jsonText = Text(shell, SWT.BORDER)
        jsonText.layoutData = GridData(GridData.FILL_HORIZONTAL)
        jsonText.addModifyListener {
            tree.traverse {
                if (jsonText.text != "") {
                    it.background = if (it.text.contains(jsonText.text)) Color(114, 188, 212) else null
                } else it.background = null
            }
        }

        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                jsonLabel.text = tree.selection.first().data as String?
            }
        })

        val button = Button(shell, SWT.PUSH)
        button.text = "depth"
        button.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val item = tree.selection.first()

            }
        })
        buildTree(builder, tree)
    }

    // auxiliar para profundidade do nó
    fun TreeItem.depth(): Int =
        if (parentItem == null) 0
        else 1 + parentItem.depth()


    fun open() {
        tree.expandAll()
        shell.pack()
        shell.open()
        val display = Display.getDefault()
        while (!shell.isDisposed) {
            if (!display.readAndDispatch()) display.sleep()
        }
        display.dispose()
    }

    fun buildTree(jValue: Visitable, treeBranch: Any, key: String? = null) {
        val treeItem = if (treeBranch is Tree) {
            TreeItem(treeBranch, SWT.None)
        } else {
            TreeItem(treeBranch as TreeItem, SWT.None)
        }
        when (jValue) {
            is Leaf -> {
                if (key != null) {
                    treeItem.text = "$key: $jValue"
                } else {
                    treeItem.text = "$jValue"
                }
                treeItem.data = jValue.toString()
            }
            is JObject -> {
                if (key != null) {
                    treeItem.text = "$key: (object)"
                } else {
                    treeItem.text = "(object)"
                }
                treeItem.data = jValue.toString()
                jValue.objects.forEach {
                    buildTree(it.value, treeItem, it.key)
                }
            }
            is JArray -> {
                treeItem.text = "$key"
                treeItem.data = jValue.toString()
                jValue.allJValues.forEach {
                    buildTree(it, treeItem)
                }
            }
        }
    }

}


// auxiliares para varrer a árvore

fun Tree.expandAll() = traverse { it.expanded = true }

fun Tree.searchAll() = traverse {}

fun Tree.traverse(visitor: (TreeItem) -> Unit) {
    fun TreeItem.traverse() {
        visitor(this)
        items.forEach {
            it.traverse()
        }
    }
    items.forEach { it.traverse() }
}




