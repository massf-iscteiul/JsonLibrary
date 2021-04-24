data class JString(val keyString: String, val valueString: String) : Visitable() {

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return "\"${keyString}\": \"${valueString}\""
    }

}