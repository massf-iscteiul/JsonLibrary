package objects

import apps.Visitor

interface JValue {
    fun accept(visitor : Visitor)
}