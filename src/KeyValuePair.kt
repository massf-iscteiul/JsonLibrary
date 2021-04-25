data class KeyValuePair(val key: String, val value: Visitable): Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        value.accept(visitor)
    }

    override fun toString(): String {
        return "\"${key}\": "
    }
}