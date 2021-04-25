package tests

import Movie
import MovieList
import junit.framework.Assert.assertEquals
import org.junit.Test
import visitors.ModelVisitor

class JBuilderTest {

    @Test
    fun jStringTest() {
        assertEquals(
            "\"Harry Potter\"",
            ModelVisitor().parse("Harry Potter")
        )
    }

    @Test
    fun jNumberTest() {
        assertEquals(
            "1999",
            ModelVisitor().parse(1999)
        )
    }

    @Test
    fun jBooleanTest() {
        assertEquals(
            "true",
            ModelVisitor().parse(true)
        )
    }

    @Test
    fun jNullTest() {
        assertEquals(
            "null",
            ModelVisitor().parse(null)
        )
    }

    @Test
    fun jArrayTest() {
        assertEquals(
            "[1, 2, \"Harry Potter\", false]",
            ModelVisitor().parse(listOf(1, 2, "Harry Potter", false))
        )
        assertEquals(
            "[]",
            ModelVisitor().parse(mutableListOf<Any>())
        )
    }

    @Test
    fun jObjectTest() {
        val movie = Movie("Harry Potter", 1, null)
        val movieList = MovieList(10, movie, true, listOf(1, 2, 3))
        assertEquals(
            "{\"liked\": true, \"list\": [1, 2, 3], \"movie\": {\"name\": \"Harry Potter\", \"number\": 1, \"series\": null}, \"score\": 10}",
            ModelVisitor().parse(movieList)
        )
        assertEquals(
            "{}",
            ModelVisitor().parse(Any())
        )
    }
}