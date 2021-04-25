package visitors

import objects.*

class ModelVisitor : Visitor {
    var numberOfStrings = 0
    private var attemptString = ""

    override fun visit(jString: JString) {
        numberOfStrings++
        attemptString += "${jString}, "
    }

    override fun visit(jNumber: JNumber) {
        attemptString += "${jNumber}, "
    }

    override fun visit(jObject: JObject){
        attemptString += "${jObject.beginString()} "
    }

    override fun endVisit(jObject: JObject) {
        attemptString = attemptString.dropLast(2)
        attemptString += " ${jObject.endString()}, "
    }

    override fun visit(jArray: JArray) {
        attemptString += jArray.beginString()
    }


    override fun endVisit(jArray: JArray) {
        attemptString = attemptString.dropLast(2)
        attemptString += "${jArray.endString()}, "
    }

    override fun visit(jPair: KeyValuePair) {
        attemptString += jPair.toString()
    }

    private fun startBuildingJson(toBeParsed: Any){
        attemptString = ""
        when (toBeParsed) {
            is String -> {
                JString(toBeParsed).accept(this)
            }
            is Int -> {
                JNumber(toBeParsed).accept(this)
            }
            is List<*> -> {
                JArray(toBeParsed).accept(this)
            }
            else -> JObject(toBeParsed).accept(this)
        }
    }

    fun parse(toBeParsed : Any): String{
        startBuildingJson(toBeParsed)
        attemptString = attemptString.dropLast(2)
        return attemptString
    }


}