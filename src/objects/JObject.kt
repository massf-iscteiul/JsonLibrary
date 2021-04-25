package objects

import visitors.Visitor
import kotlin.reflect.full.declaredMemberProperties

data class JObject(
    val name: String,
    val classObject: Any
) : Visitable() {

    constructor(classObject: Any) : this("", classObject)

    private fun instantiate(key: String, attribute: Any): Visitable {
        return when (attribute) {
            is String -> {
                KeyValuePair(key, JString(attribute))
            }
            is Int -> {
                KeyValuePair(key, JNumber(attribute))
            }
            is List<*> -> {
                KeyValuePair(key, JArray(attribute))
            }
            else -> {
                KeyValuePair(key, JObject(attribute))
            }
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        classObject::class.declaredMemberProperties.forEach {
            it.call(classObject)?.let { value -> instantiate(it.name, value).accept(visitor) }
        }
        visitor.endVisit(this)
    }

    fun beginString(): String {
        return "{"
    }

    fun endString(): String {
        return "}"
    }
}