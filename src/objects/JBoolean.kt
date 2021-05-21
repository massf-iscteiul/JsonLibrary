package objects

data class JBoolean(val value: Boolean) : JLeaf() {
    override fun toString(): String{
        return "$value"
    }
}