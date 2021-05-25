package apps

import objects.JComposite
import objects.*

interface Visitor {
    fun visit(jJLeaf: JLeaf)
    fun visit(jComposite: JComposite)
}