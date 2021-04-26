package objects

import visitors.Visitor

abstract class Leaf : Visitable {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}