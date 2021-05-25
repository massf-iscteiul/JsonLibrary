package objects

import visitors.Visitor

abstract class JLeaf : JValue {
    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }
}