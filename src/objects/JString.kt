package objects

data class JString(val value: String) : Leaf() {
    override fun toString(): String{
        return "\"${value}\""
    }
}