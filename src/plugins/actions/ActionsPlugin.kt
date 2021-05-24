package plugins.actions

import JTree

interface ActionsPlugin {
    val name: String
    fun execute(jTree: JTree)
}