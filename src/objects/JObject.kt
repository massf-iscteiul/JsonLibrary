package objects

data class JObject(val objects : MutableList<KeyValuePair>): JComposite() {

    override val allJValues : MutableList<JValue> = mutableListOf()

    init {
        objects.forEach { allJValues.add(it.value) }
    }

    override fun toString(): String{
        return "{${objects.joinToString(separator = ", ") { it.toString() }}}"
    }
}