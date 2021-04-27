package visitors

import objects.*

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

    fun search(classObject : Any?): String{
        conditionedList = mutableListOf<Visitable>()
        instantiate(classObject)
        return conditionedList.toString()
    }

}