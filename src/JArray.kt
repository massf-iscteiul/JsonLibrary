data class JArray(val keyString: String, val valuesObject: List<*>) : Visitable() {

    var visitableList = mutableListOf<Visitable>()

    private fun instantiate(attribute: Any): Visitable {
        return when (attribute) {
            is String -> {
                JString(attribute)
            }
            is Int -> {
                JNumber(attribute)
            }
            is List<*> -> {
                JArray("", attribute)
            }
            else -> {
                JObject(attribute)
            }
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        valuesObject.forEach {
            instantiate(it!!).accept(visitor)
        }
        visitor.endVisit(this)

    }

    fun firstToString(): String {
        return if (keyString == "") {
            "["
        } else "\"${keyString}\": ["
    }

    fun endString(): String{
        return "]"
    }

}