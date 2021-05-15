package visitors

import JIdentifier
import JIgnore
import objects.*
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.hasAnnotation

class JBuilder {

    fun instantiate(toBeParsed: Any?): Visitable {
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
                RJArray(arrayObjects.toMutableList())
            }
            is Map<*, *> -> {
                val keyValuePairs = mutableListOf<RKeyValuePair>()
                toBeParsed.toList().forEach{
                    keyValuePairs.add(RKeyValuePair(it.first.toString(), instantiate(it.second)))
                }
                RJObject(keyValuePairs)
            }
            null -> {
                JNull(toBeParsed)
            }
            is Enum<*> -> {
                JString(toBeParsed.name)
            }
            else -> {
                val keyValuePairs = mutableListOf<RKeyValuePair>()
                toBeParsed::class.declaredMemberProperties.forEach {
                    if (!it.hasAnnotation<JIgnore>()) {
                        val key =
                            if (it.hasAnnotation<JIdentifier>()) it.findAnnotation<JIdentifier>()!!.identifier else it.name
                        keyValuePairs.add(RKeyValuePair(key, instantiate(it.call(toBeParsed))))
                    }
                }
                RJObject(keyValuePairs)
            }
        }
    }
}