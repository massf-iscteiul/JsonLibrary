package objects

import visitors.Visitor

data class JBoolean(val value: Boolean) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "$value"
    }

}