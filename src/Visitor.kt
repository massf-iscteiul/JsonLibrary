interface Visitor {

    fun visit(jString: JString)
    fun visit(jNumber: JNumber)
    fun visit(jObject: JObject)
    fun endVisit(jObject: JObject)

}