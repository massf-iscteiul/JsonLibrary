package objects

import visitors.Visitor

data class RKeyValuePair(val key: String, val value: Visitable){
    override fun toString(): String {
        return "\"${key}\": $value"
    }
}