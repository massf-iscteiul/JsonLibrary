package visitors

import JComposite
import objects.Leaf
import objects.Visitable

class JSearcher(val condition: (Visitable) -> Boolean) : Visitor {

    var conditionedList = mutableListOf<Visitable>()

    override fun visit(jLeaf: Leaf) {
        if(condition(jLeaf)){
            conditionedList.add(jLeaf)
        }
    }

    override fun visit(jComposite: JComposite) {
        if(condition(jComposite)){
            conditionedList.add(jComposite)
        }
    }
}