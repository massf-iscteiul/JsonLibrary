import objects.JArray
import objects.JObject
import objects.Leaf
import objects.Visitable
import org.eclipse.swt.SWT
import org.eclipse.swt.events.SelectionAdapter
import org.eclipse.swt.events.SelectionEvent
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.*

class JTree(builder: Visitable) {
    val shell: Shell
    val tree: Tree

    init {
        shell = Shell(Display.getDefault())
        shell.setSize(250, 200)
        shell.text = "Json Visualizer"
        shell.layout = GridLayout(1, false)

        tree = Tree(shell, SWT.SINGLE or SWT.BORDER)


        tree.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                println("selected: " + tree.selection.first().data)
            }
        })

        val label = Label(shell, SWT.NONE)
        label.text = "skeleton"

        val button = Button(shell, SWT.PUSH)
        button.text = "depth"
        button.addSelectionListener(object : SelectionAdapter() {
            override fun widgetSelected(e: SelectionEvent) {
                val item = tree.selection.first()
                label.text = item.depth().toString()
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
                }
                else {
                    treeItem.text = "$jValue"
                }
            }
            is JObject -> {
                if (key != null) {
                    treeItem.text = "$key: (object)"
                }
                else {
                    treeItem.text = "(object)"
                }
                jValue.objects.forEach {
                    buildTree(it.value, treeItem, it.key)
                }
            }
            is JArray -> {
                treeItem.text = "$key"
                jValue.allJValues.forEach {
                    buildTree(it, treeItem)
                }
            }
        }
    }

}


// auxiliares para varrer a árvore

fun Tree.expandAll() = traverse { it.expanded = true }

fun Tree.traverse(visitor: (TreeItem) -> Unit) {
    fun TreeItem.traverse() {
        visitor(this)
        items.forEach {
            it.traverse()
        }
    }
    items.forEach { it.traverse() }


}




