package plugins.presentation

import JTree

interface PresentationPlugin {
    fun execute(jTree: JTree)
}