package objects

data class JString(val value: String) : JLeaf() {
    override fun toString(): String{
        return "\"${value}\""
    }
}