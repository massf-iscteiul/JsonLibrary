package plugins.actions

import JTree
import objects.JObject

class RemoveItem : ActionsPlugin {

    override val name: String
        get() = "Remove"

    override fun execute(jTree: JTree) {
        val treeItem = jTree.tree.selection.first()
        (treeItem.parentItem.data as JObject).objects.removeIf { keyValuePair ->
            keyValuePair.value == treeItem.data
        }
        jTree.refresh()
    }

}