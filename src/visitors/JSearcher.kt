package visitors

import JComposite
import objects.JLeaf
import objects.Visitable

class JSearcher(val condition: (Visitable) -> Boolean) : Visitor {

    var conditionedList = mutableListOf<Visitable>()

    override fun visit(jJLeaf: JLeaf) {
        if(condition(jJLeaf)){
            conditionedList.add(jJLeaf)
        }
    }

    override fun visit(jComposite: JComposite) {
        if(condition(jComposite)){
            conditionedList.add(jComposite)
        }
    }
}