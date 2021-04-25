import visitors.ModelVisitor

fun main(){
    val visitor = ModelVisitor()
    val movie = Movie("Harry Potter",1)
    val movieList = MovieList(10, movie, "Yes", listOf(1,2,3))
    val movieJson = visitor.parse(movieList)
    println(movieJson)
    val jsonArray = visitor.parse(listOf(1,2,3, "hihi"))
    println(jsonArray)
    val jsonString = visitor.parse("Hello World")
    println(jsonString)
    val jsonNumber = visitor.parse(69420)
    println(jsonNumber)
}