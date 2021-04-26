import visitors.JBuilder

fun main() {
    val visitor = JBuilder()
    val movie = Movie("Harry Potter", 1, null)
    val movieList = MovieList(10, movie, true, listOf("Parte 1", "Parte 2"))
    val movieJson = visitor.parse(movieList)
    println(movieJson)
}