import tests.testobjects.Movie
import tests.testobjects.MovieList
import apps.JBuilder
import apps.JTreeWindow

fun main() {
    val movie = Movie("Harry Potter", 1, null)
    val movieList = MovieList(10, movie, true, listOf(1, 2, 3))
    val builder = JBuilder().instantiate(movieList)
    val w = Injector.create(JTreeWindow(builder))
    w.open()
}