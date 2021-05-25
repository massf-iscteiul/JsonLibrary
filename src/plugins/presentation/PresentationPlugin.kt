package plugins.presentation

import JTreeWindow

interface PresentationPlugin {
    fun execute(jTreeWindow: JTreeWindow)
}