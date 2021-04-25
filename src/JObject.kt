import kotlin.reflect.full.declaredMemberProperties

data class JObject(
    val name: String,
    val classObject: Any
) : Visitable() {

    constructor(classObject: Any) : this("", classObject)

    private fun instantiate(key: String, attribute: Any): Visitable {
        return when (attribute) {
            is String -> {
                JString(key, attribute)
            }
            is Int -> {
                JNumber(key, attribute)
            }
            is List<*> -> {
                JArray(key, attribute)
            }
            else -> {
                JObject(key, attribute)
            }
        }
    }

    private fun checkType(arg: Any) : Boolean{
        return when (arg) {
            is String -> {
                true
            }
            is Int -> {
                true
            }
            else -> arg is List<*>
        }
    }

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        if (!checkType(classObject)) {
            classObject::class.declaredMemberProperties.forEach {
                it.call(classObject)?.let { value -> instantiate(it.name, value).accept(visitor) }
            }
        }
        else {
            instantiate("", classObject).accept(visitor)
        }
        visitor.endVisit(this)
    }

    fun firstToString(): String {
        return if (name == "") {
            "{ "
        } else "\"${name}\": { "
    }

    fun endString(): String{
        return if (name == "") {
            "}"
        } else "},"
    }
}