package tests

import Movie
import MovieList
import junit.framework.Assert.assertEquals
import org.junit.Test
import visitors.JBuilder

class JBuilderTest {

    @Test
    fun jStringTest() {
        assertEquals(
            "\"Harry Potter\"",
            JBuilder().parse("Harry Potter")
        )
    }

    @Test
    fun jNumberTest() {
        assertEquals(
            "1999",
            JBuilder().parse(1999)
        )
    }

    @Test
    fun jBooleanTest() {
        assertEquals(
            "true",
            JBuilder().parse(true)
        )
    }

    @Test
    fun jNullTest() {
        assertEquals(
            "null",
            JBuilder().parse(null)
        )
    }

    @Test
    fun jArrayTest() {
        assertEquals(
            "[1, 2, \"Harry Potter\", false]",
            JBuilder().parse(listOf(1, 2, "Harry Potter", false))
        )
        assertEquals(
            "[]",
            JBuilder().parse(mutableListOf<Any>())
        )
    }

    @Test
    fun jObjectTest() {
        val movie = Movie("Harry Potter", 1, null)
        val movieList = MovieList(10, movie, true, listOf(1, 2, 3))
        assertEquals(
            "{\"liked\": true, \"movie\": {\"name\": \"Harry Potter\", \"number\": 1, \"series\": null}, \"related\": [1, 2, 3], \"score\": 10}",
            JBuilder().parse(movieList)
        )
        assertEquals(
            "{}",
            JBuilder().parse(Any())
        )
    }
}