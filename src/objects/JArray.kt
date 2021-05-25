package objects

import JComposite

data class JArray(override val allJValues: MutableList<JValue>) : JComposite() {
    override fun toString(): String{
        return "[${allJValues.joinToString(separator = ", ") {it.toString()}}]"
    }
}