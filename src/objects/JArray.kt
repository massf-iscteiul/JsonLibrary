package objects

import JComposite

data class JArray(override val allJValues: MutableList<Visitable>) : JComposite() {
    override fun toString(): String{
        return "[${allJValues.joinToString(separator = ", ") {it.toString()}}]"
    }
}