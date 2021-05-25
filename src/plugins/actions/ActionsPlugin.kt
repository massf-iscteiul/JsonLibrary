package plugins.actions

import apps.JTreeWindow

interface ActionsPlugin {
    val name: String
    fun execute(jTreeWindow: JTreeWindow)
}