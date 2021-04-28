package objects

import visitors.Visitor

data class JArray(val values: List<*>) : Composite() {

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
            is List<*>, is Set<*> -> {
                JArray((attribute as Iterable<*>).toList())
            }
            null -> {
                JNull(attribute)
            }
            is Enum<*> -> {
                JString(attribute.name)
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

    override fun beginString(): String {
        return "["
    }

    override fun endString(): String{
        return "]"
    }

}