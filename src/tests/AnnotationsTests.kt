package tests

import JIdentifier
import JIgnore
import junit.framework.Assert.assertEquals
import org.junit.Test
import apps.JBuilder

class AnnotationsTests {

    data class Book(
        val name: String,
        val volume: Int,
        @JIgnore
        val otherVolumes: List<Int>,
        val interesting: Boolean
    )

    data class Journal(
        val owner: String,
        val age: Int,
        @JIdentifier("confidential")
        val favoriteNumbers: List<Int>,
        val canRead: Boolean
    )

    @Test
    fun ignoreTest() {
        val book = Book("1001 Jokes", 2, listOf(1,3,4), true)
        assertEquals(
            "{\"interesting\": true, \"name\": \"1001 Jokes\", \"volume\": 2}",
            JBuilder().instantiate(book).toString()
        )
    }

    @Test
    fun identifierTest() {
        val journal = Journal("Miguel", 21, listOf(1,2,3,4), false)
        assertEquals(
            "{\"age\": 21, \"canRead\": false, \"confidential\": [1, 2, 3, 4], \"owner\": \"Miguel\"}",
            JBuilder().instantiate(journal).toString()
        )
    }


}