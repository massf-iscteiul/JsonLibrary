package plugins.presentation

import apps.JTreeWindow

interface PresentationPlugin {
    fun execute(jTreeWindow: JTreeWindow)
}