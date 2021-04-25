package visitors

import objects.*

interface Visitor {

    fun visit(jString: JString)
    fun visit(jNumber: JNumber)
    fun visit(jBoolean: JBoolean)
    fun visit(jNull: JNull)
    fun visit(jArray: JArray)
    fun endVisit(jArray: JArray)
    fun visit(jObject: JObject)
    fun endVisit(jObject: JObject)
    fun visit(jPair: KeyValuePair)
    fun endVisit(visitable: Visitable)


}