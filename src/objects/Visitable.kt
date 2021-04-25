package objects

import visitors.Visitor

abstract class Visitable {

    abstract fun accept(visitor : Visitor)
}