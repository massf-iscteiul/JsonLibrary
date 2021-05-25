package objects

data class KeyValuePair(val key: String, val value: JValue){
    override fun toString(): String {
        return "\"${key}\": $value"
    }
}