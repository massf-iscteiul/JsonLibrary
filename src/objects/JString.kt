package objects

import visitors.Visitor

data class JString(val valueString: String) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "\"${valueString}\""
    }

}