package plugins

import JTree

interface PresentationPlugin {
    fun execute(jTree: JTree)
}