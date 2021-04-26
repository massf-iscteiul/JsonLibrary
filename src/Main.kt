import objects.*
import visitors.JBuilder
import visitors.JSearcher

fun main() {
    val visitor = JBuilder()
    val movie = Movie("Harry Potter", 1, null)
    val movieJson1 = visitor.parse(movie)
    println(movieJson1)
    val movieList = MovieList(10, movie, true, listOf(1, 2, 3, "Parte 1", "Parte 2"))
    val movieJson = visitor.parse(movieList)
    println(movieJson)
    val jsonArray = visitor.parse(listOf(1, 2, 3, null, "hihi", false))
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
    val nullObject = visitor.parse(null)
    println(nullObject)
    val searchVisitor = JSearcher { it is JString }
    println(searchVisitor.search(movieList))
    val searchVisitorComposite = JSearcher {
        it is JObject && it.allJValues.find { it2 ->
            it2.value is JNumber && it2.value.value == 1
        } != null
    }
    println(searchVisitorComposite.search(movieList))


}