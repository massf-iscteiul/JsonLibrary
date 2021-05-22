package plugins

import JTree
import java.io.File

class WriteJson : ActionsPlugin {

    override val name: String
        get() = "Write to Json"

    override fun execute(jTree: JTree) {
        val fileName = "src/jsonWritePlugin.txt"
        val jsonFile = File(fileName)
        jsonFile.printWriter().use { out ->
            out.println(jTree.jsonLabel.text)
        }
    }

}