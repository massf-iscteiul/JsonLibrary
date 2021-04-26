package objects

import JIgnore
import visitors.Visitor
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.hasAnnotation

data class JObject(val classObject: Any) : Composite() {


    val allJValues : MutableList<KeyValuePair> = mutableListOf()

    init {
        classObject::class.declaredMemberProperties.forEach {
            if(!it.hasAnnotation<JIgnore>()) {
                it.call(classObject).let { value -> allJValues.add(instantiate(it.name, value)) }
            }
        }
    }

    private fun instantiate(key: String, attribute: Any?): KeyValuePair {
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
            null -> {
                KeyValuePair(key, JNull(attribute))
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

    override fun beginString(): String {
        return "{"
    }

    override fun endString(): String {
        return "}"
    }
}