package apps

import objects.JComposite
import objects.JLeaf
import objects.JValue

class JSearcher(val condition: (JValue) -> Boolean) : Visitor {

    var conditionedList = mutableListOf<JValue>()

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