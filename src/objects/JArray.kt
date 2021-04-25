package objects

import visitors.Visitor

data class JArray(val values: List<*>) : Visitable() {

    private fun instantiate(attribute: Any?): Visitable {
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
            null -> {
                JNull(attribute)
            }
            else -> {
                JObject(attribute)
            }
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        values.forEach {
            val jValue = instantiate(it)
            jValue.accept(visitor)
            if (it != values.last()){
                visitor.endVisit(jValue)
            }
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