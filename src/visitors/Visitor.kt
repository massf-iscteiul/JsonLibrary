package visitors

import objects.*

interface Visitor {
    fun visit(jLeaf: Leaf)
    fun visit(jComposite: Composite)
    fun endVisit(jComposite: Composite)
    fun endVisit(visitable: Visitable)
}