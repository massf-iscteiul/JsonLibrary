package visitors

import RJComposite
import objects.Composite
import objects.Leaf
import objects.Visitable

class JSearcher(val condition: (Visitable) -> Boolean) : Visitor {

    var conditionedList = mutableListOf<Visitable>()

    override fun visit(jLeaf: Leaf) {
        if(condition(jLeaf)){
            conditionedList.add(jLeaf)
        }
    }

    override fun visit(jComposite: Composite) {
        if(condition(jComposite)){
            conditionedList.add(jComposite)
        }
    }

    override fun visit(rjComposite: RJComposite) {
        if(condition(rjComposite)){
            conditionedList.add(rjComposite)
        }
    }
}