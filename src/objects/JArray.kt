package objects

import visitors.Visitor

data class JArray(val valuesObject: List<*>) : Visitable() {

    private fun instantiate(attribute: Any): Visitable {
        return when (attribute) {
            is String -> {
                JString(attribute)
            }
            is Int -> {
                JNumber(attribute)
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
        valuesObject.forEach {
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