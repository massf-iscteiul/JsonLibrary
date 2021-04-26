package tests

import Movie
import MovieList
import junit.framework.Assert.assertEquals
import objects.JNumber
import objects.JObject
import objects.JString
import org.junit.Test
import visitors.JSearcher

class JSearcherTest {

    @Test
    fun jSearchStringTest() {
        val movie = Movie("Tarzan", 2, null)
        val movieList = MovieList(7, movie, true, listOf("The Lion King", "Mogly"))
        assertEquals(
            "[\"Tarzan\", \"The Lion King\", \"Mogly\"]",
            JSearcher { it is JString }.search(movieList)
        )
    }

    @Test
    fun jSearchCompositeTest(){
        val movie = Movie("Tarzan", 2, null)
        val movieList = MovieList(7, movie, true, listOf("The Lion King", "Mogly"))
        val searchVisitorComposite = JSearcher {
            it is JObject && it.allJValues.find { it2 ->
                it2.value is JNumber && it2.value.value == 7
            } != null
        }
        assertEquals(
            "[JObject(classObject=MovieList(score=7, movie=Movie(name=Tarzan, number=2, series=null), liked=true, related=[The Lion King, Mogly]))]",
            searchVisitorComposite.search(movieList)
        )
        val searchVisitorComposite2 = JSearcher {
            it is JObject && it.allJValues.find { it2 ->
                it2.value is JString && it2.key == "name" && it2.value.value == "Tarzan"
            } != null
        }
        assertEquals(
            "[JObject(classObject=Movie(name=Tarzan, number=2, series=null))]",
            searchVisitorComposite2.search(movieList)
        )
    }
}