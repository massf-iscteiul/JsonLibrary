package objects

import visitors.Visitor

interface JValue {
    fun accept(visitor : Visitor)
}