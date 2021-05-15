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
            JBuilder().instantiate("Harry Potter").toString()
        )
    }

    @Test
    fun jNumberTest() {
        assertEquals(
            "1999",
            JBuilder().instantiate(1999).toString()
        )
    }

    @Test
    fun jBooleanTest() {
        assertEquals(
            "true",
            JBuilder().instantiate(true).toString()
        )
    }

    @Test
    fun jNullTest() {
        assertEquals(
            "null",
            JBuilder().instantiate(null).toString()
        )
    }

    @Test
    fun jArrayTest() {
        assertEquals(
            "[1, 2, \"Harry Potter\", false]",
            JBuilder().instantiate(listOf(1, 2, "Harry Potter", false)).toString()
        )
        assertEquals(
            "[]",
            JBuilder().instantiate(mutableListOf<Any>()).toString()
        )
    }

    @Test
    fun jObjectTest() {
        val movie = Movie("Harry Potter", 1, null)
        val movieList = MovieList(10, movie, true, listOf(1, 2, 3))
        assertEquals(
            "{\"liked\": true, \"movie\": {\"name\": \"Harry Potter\", \"number\": 1, \"series\": null}, \"related\": [1, 2, 3], \"score\": 10}",
            JBuilder().instantiate(movieList).toString()
        )
        assertEquals(
            "{}",
            JBuilder().instantiate(Any()).toString()
        )
    }
}