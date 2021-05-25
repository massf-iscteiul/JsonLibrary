package plugins.actions

import JTreeWindow

interface ActionsPlugin {
    val name: String
    fun execute(jTreeWindow: JTreeWindow)
}