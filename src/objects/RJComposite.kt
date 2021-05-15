import objects.Visitable
import visitors.Visitor

abstract class RJComposite : Visitable {

    abstract val allJValues: MutableList<Visitable>

    override fun accept(visitor: Visitor) {
        visitor.visit(this)
        allJValues.forEach { it.accept(visitor) }
    }
}