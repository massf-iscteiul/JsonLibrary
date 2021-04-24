import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
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
            else -> {
                JObject(key, attribute)
            }
        }
    }

    override fun accept(visitor: Visitor) {
        print("heheh")
        visitor.visit(this)
        classObject::class.declaredMemberProperties.forEach {
            it.call(classObject)?.let { value -> instantiate(it.name, value).accept(visitor) }
        }
        visitor.endVisit(this)
    }

    fun firstToString(): String {
        return if (name == "") {
            "{\n"
        } else "\"${name}\": {\n"
    }

    fun endString(): String{
        return if (name == "") {
            "}"
        } else "},"
    }
}