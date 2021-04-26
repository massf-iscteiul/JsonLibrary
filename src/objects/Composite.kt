package objects

import visitors.Visitor

abstract class Composite : Visitable {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    abstract fun beginString(): Any?
    abstract fun endString(): Any?
}