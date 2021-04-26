package objects

data class JNumber(val value: Int) : Leaf() {
    override fun toString(): String{
        return "$value"
    }
}