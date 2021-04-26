package objects

import visitors.Visitor

data class KeyValuePair(val key: String, val value: Visitable): Leaf() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        value.accept(visitor)
    }

    override fun toString(): String {
        return "\"${key}\": "
    }
}