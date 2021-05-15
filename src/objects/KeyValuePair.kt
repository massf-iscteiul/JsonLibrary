package objects

data class KeyValuePair(val key: String, val value: Visitable){
    override fun toString(): String {
        return "\"${key}\": $value"
    }
}