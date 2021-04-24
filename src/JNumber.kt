data class JNumber(val keyString: String, val valueInt: Int) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "\"${keyString}\": $valueInt"
    }
}