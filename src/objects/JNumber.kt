package objects

data class JNumber(val value: Int) : JLeaf() {
    override fun toString(): String{
        return "$value"
    }
}