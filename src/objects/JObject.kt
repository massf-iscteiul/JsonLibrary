package objects

import visitors.Visitor
import kotlin.reflect.full.declaredMemberProperties

data class JObject(
    val name: String,
    val classObject: Any
) : Visitable() {

    constructor(classObject: Any) : this("", classObject)

    private val allJValues : MutableList<KeyValuePair> = mutableListOf()

    init {
        classObject::class.declaredMemberProperties.forEach {
            it.call(classObject)?.let { value -> allJValues.add(instantiate(it.name, value)) }
        }
    }

    private fun instantiate(key: String, attribute: Any): KeyValuePair {
        return when (attribute) {
            is String -> {
                KeyValuePair(key, JString(attribute))
            }
            is Int -> {
                KeyValuePair(key, JNumber(attribute))
            }
            is Boolean -> {
                KeyValuePair(key, JBoolean(attribute))
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
        allJValues.forEach {
            it.accept(visitor)
            if (it != allJValues.last()){
                visitor.endVisit(it)
            }
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