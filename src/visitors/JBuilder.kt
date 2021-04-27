package visitors

import objects.*

class JBuilder : Visitor {
    private var attemptString = ""

    override fun visit(jLeaf: Leaf) {
        attemptString += jLeaf.toString()
    }

    override fun visit(jComposite: Composite) {
        attemptString += jComposite.beginString()
    }

    override fun endVisit(jComposite: Composite) {
        attemptString += jComposite.endString()
    }

    override fun endVisit(visitable: Visitable) {
        attemptString += ", "
    }

    fun parse(toBeParsed : Any?): String{
        attemptString = ""
        instantiate(toBeParsed)
        return attemptString
    }


}