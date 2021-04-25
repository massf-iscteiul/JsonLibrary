package objects

import visitors.Visitor

data class JNull(val value : Any?) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "null"
    }

}