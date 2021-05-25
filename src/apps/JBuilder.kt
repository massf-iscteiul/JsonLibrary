package apps

import JIdentifier
import JIgnore
import objects.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class JBuilder {

    fun instantiate(toBeParsed: Any?): JValue {
        return when (toBeParsed) {
            is String -> {
                JString(toBeParsed)
            }
            is Int -> {
                JNumber(toBeParsed)
            }
            is Boolean -> {
                JBoolean(toBeParsed)
            }
            is List<*>, is Set<*> -> {
                val arrayObjects = (toBeParsed as Iterable<*>).map { instantiate(it) }
                JArray(arrayObjects.toMutableList())
            }
            is Map<*, *> -> {
                val keyValuePairs = mutableListOf<KeyValuePair>()
                toBeParsed.toList().forEach{
                    keyValuePairs.add(KeyValuePair(it.first.toString(), instantiate(it.second)))
                }
                JObject(keyValuePairs)
            }
            null -> {
                JNull(toBeParsed)
            }
            is Enum<*> -> {
                JString(toBeParsed.name)
            }
            else -> {
                val keyValuePairs = mutableListOf<KeyValuePair>()
                toBeParsed::class.declaredMemberProperties.forEach {
                    if (!it.hasAnnotation<JIgnore>()) {
                        val key =
                            if (it.hasAnnotation<JIdentifier>()) it.findAnnotation<JIdentifier>()!!.identifier else it.name
                        keyValuePairs.add(KeyValuePair(key, instantiate(it.call(toBeParsed))))
                    }
                }
                JObject(keyValuePairs)
            }
        }
    }
}