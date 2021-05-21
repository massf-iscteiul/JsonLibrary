package objects

data class JNull(val value : Any?) : JLeaf() {
    override fun toString(): String{
        return "null"
    }
}