package objects

import JIdentifier
import JIgnore
import visitors.Visitor
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

data class JObject(val classObject: Any) : Composite() {


    val allJValues : MutableList<KeyValuePair> = mutableListOf()

    init {
        classObject::class.declaredMemberProperties.forEach {
            if(!it.hasAnnotation<JIgnore>()) {
                val key = if (it.hasAnnotation<JIdentifier>()) it.findAnnotation<JIdentifier>()!!.identifier else it.name
                it.call(classObject).let { value ->
                    allJValues.add(instantiate(key, value)) }
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
            is List<*>, is Set<*> -> {
                KeyValuePair(key, JArray((attribute as Iterable<*>).toList()))
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