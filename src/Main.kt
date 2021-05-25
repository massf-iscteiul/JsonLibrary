import tests.testobjects.Movie
import tests.testobjects.MovieList
import visitors.JBuilder

data class Something(val movieList: List<Movie>, val something: String)

fun main() {
    val movie = Movie("Harry Potter", 1, null)
    val movieList = MovieList(10, movie, true, listOf(1, 2, 3))
    val stringTest = "Hello World"
    val something = Something(listOf(movie, movie,movie), "SomethingTest")
    val builder = JBuilder().instantiate(movieList)
    //println(builder.toString())
    val w = Injector.create(JTreeWindow(builder))
    w.open()

}