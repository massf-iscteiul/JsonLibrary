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

fun main() {


}