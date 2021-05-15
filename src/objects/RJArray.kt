package objects

import RJComposite

data class RJArray(override val allJValues: MutableList<Visitable>) : RJComposite() {

    override fun beginString(): Any? {
        TODO("Not yet implemented")
    }

    override fun endString(): Any? {
        TODO("Not yet implemented")
    }

    override fun toString(): String{
        return "[${allJValues.joinToString(separator = ", ") {it.toString()}}]"
    }
}