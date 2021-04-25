package objects

import visitors.Visitor

data class JNumber(val value: Int) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "$value"
    }
}