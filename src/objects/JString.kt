package objects

data class JString(var value: String) : JLeaf() {
    override fun toString(): String{
        return "\"${value}\""
    }
}