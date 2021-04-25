data class JString(val keyString: String, val valueString: String) : Visitable() {

    constructor(valueString: String) : this("", valueString)

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
    }

    override fun toString(): String{
        return if (keyString == "") {
            "\"$valueString\""
        } else "\"${keyString}\": \"${valueString}\""
    }

}