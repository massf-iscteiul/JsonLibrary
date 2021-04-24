import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class ModelVisitor : Visitor {
    var numberOfStrings = 0
    var finalString = ""
    var json = mutableListOf<String>()

    override fun visit(jString: JString) {
        numberOfStrings++
        // json.add(jString.toString())
        finalString += "$jString,\n"
    }

    override fun visit(jNumber: JNumber) {
        // json.add(jNumber.toString())
        finalString += "$jNumber,\n"
    }

    override fun visit(jObject: JObject){
        finalString+=jObject.firstToString()
        // finalString = jObject.toString(json.joinToString(separator = ",\n"))
    }

    override fun endVisit(jObject: JObject) {
        finalString = finalString.dropLast(2)
        finalString+="\n${jObject.endString()}\n"
    }

    fun parse(toBeParsed : Any): String{
        val jObject = JObject(toBeParsed)
        jObject.accept(this)
        return finalString
    }


}