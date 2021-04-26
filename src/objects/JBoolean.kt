package objects

data class JBoolean(val value: Boolean) : Leaf() {
    override fun toString(): String{
        return "$value"
    }
}