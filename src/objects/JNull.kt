package objects

data class JNull(val value : Any?) : Leaf() {
    override fun toString(): String{
        return "null"
    }
}