package visitors

import objects.*

interface Visitor {
    fun visit(jLeaf: Leaf)
    fun visit(jComposite: Composite)
    fun endVisit(jComposite: Composite) {}
    fun endVisit(visitable: Visitable) {}
    fun instantiate(toBeParsed: Any?){
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
            is List<*>, is Set<*> -> {
                JArray((toBeParsed as Iterable<*>).toList()).accept(this)
            }
            null -> {
                JNull(toBeParsed).accept(this)
            }
            else -> JObject(toBeParsed).accept(this)
        }
    }
}