package objects

import visitors.Visitor

data class JArray(val values: List<*>) : Visitable() {

    private fun instantiate(attribute: Any): Visitable {
        return when (attribute) {
            is String -> {
                JString(attribute)
            }
            is Int -> {
                JNumber(attribute)
            }
            is Boolean -> {
                JBoolean(attribute)
            }
            is List<*> -> {
                JArray(attribute)
            }
            else -> {
                JObject(attribute)
            }
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        values.forEach {
            instantiate(it!!).accept(visitor)
        }
        visitor.endVisit(this)

    }

    fun beginString(): String {
        return "["
    }

    fun endString(): String{
        return "]"
    }

}