package objects

import RJComposite

data class RJArray(override val allJValues: MutableList<Visitable>) : RJComposite() {
    override fun toString(): String{
        return "[${allJValues.joinToString(separator = ", ") {it.toString()}}]"
    }
}