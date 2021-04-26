package objects

import visitors.Visitor

interface Visitable {
    fun accept(visitor : Visitor)
}