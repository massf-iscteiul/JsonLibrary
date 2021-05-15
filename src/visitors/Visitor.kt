package visitors

import RJComposite
import objects.*

interface Visitor {
    fun visit(jLeaf: Leaf)
    fun visit(rjComposite: RJComposite)
}