package objects

data class JBoolean(var value: Boolean) : JLeaf() {
    override fun toString(): String{
        return "$value"
    }
}