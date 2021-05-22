package plugins

import JComposite
import JTree
import objects.JLeaf
import objects.JNumber
import org.eclipse.swt.graphics.Image
import org.eclipse.swt.graphics.ImageData
import org.eclipse.swt.widgets.Display
import org.eclipse.swt.widgets.TreeItem
import utils.traverse
class IconsPlugin: PresentationPlugin {

    override fun execute(jTtree: JTree) {
        val wrong = mutableListOf<Any>()
        jTtree.tree.traverse {
            it.text = it.text.split(":")[0]
            if (it.data is JNumber){
                wrong.add(it)
            }else {
                when (it.data) {
                    is JLeaf -> {
                        it.image = Image(Display.getDefault(), ImageData("src/icons/file.png").scaledTo(25, 25))
                    }
                    is JComposite -> {
                        it.image = Image(Display.getDefault(), ImageData("src/icons/folder.png").scaledTo(25, 25))
                    }
                }
            }
        }
        wrong.forEach {(it as TreeItem).dispose()}
    }

}