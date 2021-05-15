package visitors

import RJComposite
import objects.Leaf
import objects.Visitable

class JSearcher(val condition: (Visitable) -> Boolean) : Visitor {

    var conditionedList = mutableListOf<Visitable>()

    override fun visit(jLeaf: Leaf) {
        if(condition(jLeaf)){
            conditionedList.add(jLeaf)
        }
    }

    override fun visit(rjComposite: RJComposite) {
        if(condition(rjComposite)){
            conditionedList.add(rjComposite)
        }
    }
}