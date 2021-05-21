package objects

import visitors.Visitor

abstract class JLeaf : Visitable {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}