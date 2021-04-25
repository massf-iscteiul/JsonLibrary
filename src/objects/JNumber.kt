package objects

import visitors.Visitor

data class JNumber(val keyString: String, val valueInt: Int) : Visitable() {

    constructor(valueInt: Int) : this("", valueInt)

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "$valueInt"
    }
}