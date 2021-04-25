package visitors

import objects.*

interface Visitor {

    fun visit(jString: JString)
    fun visit(jNumber: JNumber)
    fun visit(jBoolean: JBoolean)
    fun visit(jObject: JObject)
    fun endVisit(jObject: JObject)
    fun visit(jArray: JArray)
    fun endVisit(jArray: JArray)
    fun visit(jPair: KeyValuePair)


}