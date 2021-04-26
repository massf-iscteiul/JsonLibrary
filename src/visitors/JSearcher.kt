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

    private fun startBuildingJson(toBeParsed: Any?){
        conditionedList = mutableListOf<Visitable>()
        when (toBeParsed) {
            is String -> {
                JString(toBeParsed).accept(this)
            }
            is Int -> {
                JNumber(toBeParsed).accept(this)
            }
            is Boolean -> {
                JBoolean(toBeParsed).accept(this)
            }
            is List<*> -> {
                JArray(toBeParsed).accept(this)
            }
            null -> {
                JNull(toBeParsed).accept(this)
            }
            else -> JObject(toBeParsed).accept(this)
        }
    }

    fun search(classObject : Any?): String{
        startBuildingJson(classObject)
        return conditionedList.toString()
    }

}