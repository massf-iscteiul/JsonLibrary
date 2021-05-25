import objects.JValue
import apps.Visitor

abstract class JComposite : JValue {

    abstract val allJValues: MutableList<JValue>

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        allJValues.forEach { it.accept(visitor) }
    }
}