package visitors

import objects.*

class ModelVisitor : Visitor {
    private var attemptString = ""

    override fun visit(jLeaf: Leaf) {
        attemptString += jLeaf.toString()
    }

    override fun visit(jComposite: Composite) {
        attemptString += jComposite.beginString()
    }

    override fun endVisit(jComposite: Composite) {
        attemptString += jComposite.endString()
    }

    override fun endVisit(visitable: Visitable) {
        attemptString += ", "
    }

    private fun startBuildingJson(toBeParsed: Any?){
        attemptString = ""
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

    fun parse(toBeParsed : Any?): String{
        startBuildingJson(toBeParsed)
        return attemptString
    }


}