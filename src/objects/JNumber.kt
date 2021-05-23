package objects

data class JNumber(var value: Int) : JLeaf() {
    override fun toString(): String{
        return "$value"
    }
}