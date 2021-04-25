import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties

class ModelVisitor : Visitor {
    var numberOfStrings = 0
    var finalString = ""
    var json = mutableListOf<String>()

    override fun visit(jString: JString) {
        numberOfStrings++
        finalString += "$jString, "
    }

    override fun visit(jNumber: JNumber) {
        finalString += "$jNumber, "
    }

    override fun visit(jObject: JObject){
        finalString+=jObject.firstToString()
    }


    override fun endVisit(jObject: JObject) {
        finalString = finalString.dropLast(2)
        finalString+=" ${jObject.endString()} "
    }

    override fun visit(jArray: JArray) {
        finalString += jArray.firstToString()
    }

    override fun endVisit(jArray: JArray) {
        finalString = finalString.dropLast(2)
        finalString += "${jArray.endString()},\n"
    }

    fun parse(toBeParsed : Any): String{
        val jObject = JObject(toBeParsed)
        jObject.accept(this)
        return finalString
    }


}