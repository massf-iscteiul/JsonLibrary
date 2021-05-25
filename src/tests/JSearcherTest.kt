package tests

import tests.testobjects.Movie
import tests.testobjects.MovieList
import junit.framework.Assert.assertEquals
import objects.JNumber
import objects.JString
import objects.JObject
import org.junit.Test
import visitors.JBuilder
import visitors.JSearcher

class JSearcherTest {

    @Test
    fun jSearchStringTest() {
        val movie = Movie("Tarzan", 2, null)
        val movieList = MovieList(7, movie, true, listOf("The Lion King", "Mogly"))
        val searchVisitor = JSearcher { it is JString }
        val builder = JBuilder()
        builder.instantiate(movieList).accept(searchVisitor)
        assertEquals(
            "[\"Tarzan\", \"The Lion King\", \"Mogly\"]",
            searchVisitor.conditionedList.toString()
        )
    }

    @Test
    fun jSearchCompositeTest() {
        val movie = Movie("Tarzan", 2, null)
        val movieList = MovieList(7, movie, true, listOf("The Lion King", "Mogly"))
        val searchVisitor = JSearcher {
            it is JObject && it.objects.find { it2 ->
                it2.value is JNumber && it2.value.value == 2
            } != null
        }
        val builder = JBuilder()
        builder.instantiate(movieList).accept(searchVisitor)
        assertEquals(
            "[{\"name\": \"Tarzan\", \"number\": 2, \"series\": null}]",
            searchVisitor.conditionedList.toString()
        )

        val searchVisitor2 = JSearcher {
            it is JObject && it.objects.find { it2 ->
                it2.value is JString && it2.key == "name" && it2.value.value == "Tarzan"
            } != null
        }
        builder.instantiate(movieList).accept(searchVisitor2)
        assertEquals(
            "[{\"name\": \"Tarzan\", \"number\": 2, \"series\": null}]",
            searchVisitor2.conditionedList.toString()
        )
    }
}