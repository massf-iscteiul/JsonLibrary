import visitors.ModelVisitor

fun main(){
    val visitor = ModelVisitor()
    val movie = Movie("Harry Potter",1)
    val movieList = MovieList(10, movie, true, listOf(1,2,3))
    val movieJson = visitor.parse(movieList)
    println(movieJson)
    val jsonArray = visitor.parse(listOf(1,2,3, "hihi", false))
    println(jsonArray)
    val jsonString = visitor.parse("Hello World")
    println(jsonString)
    val jsonNumber = visitor.parse(69420)
    println(jsonNumber)
    val jsonBoolean = visitor.parse(true)
    println(jsonBoolean)
    val emptyArray = visitor.parse(mutableListOf<Any>())
    println(emptyArray)
    val emptyObject = visitor.parse(Any())
    println(emptyObject)
}