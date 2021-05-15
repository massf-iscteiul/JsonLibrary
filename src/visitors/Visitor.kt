package visitors

import JComposite
import objects.*

interface Visitor {
    fun visit(jLeaf: Leaf)
    fun visit(jComposite: JComposite)
}