package objects

import RJComposite

data class RJObject(val objects : List<RKeyValuePair>): RJComposite() {

    override val allJValues : MutableList<Visitable> = mutableListOf()

    init {
        objects.forEach { allJValues.add(it.value) }
    }

    override fun toString(): String{
        return "{${objects.joinToString(separator = ", ") { it.toString() }}}"
    }
    override fun beginString(): Any? {
        TODO("Not yet implemented")
    }

    override fun endString(): Any? {
        TODO("Not yet implemented")
    }
}