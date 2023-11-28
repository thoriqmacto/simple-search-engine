package search

enum class Rainbow(val colorName: String) {
    RED("Red"),
    ORANGE("Orange"),
    YELLOW("Yellow"),
    GREEN("Green"),
    BLUE("Blue"),
    INDIGO("Indigo"),
    VIOLET("Violet"),
    NULL("")
}

fun findByName(name: String): Rainbow {
    for (color in Rainbow.values()) {
        if (name == color.colorName) return color
    }
    return Rainbow.NULL
}

//fun main() {
//    val color = findByName("Black")
//    println(color.ordinal)
//}

// Sample inverted index (word to lines)
val invertedIndex = mapOf(
    "apple" to listOf(1, 3, 5),
    "banana" to listOf(2, 4),
    "orange" to listOf(1, 2, 3),
)

fun main() {
    val query = "apple orange" // Replace with your query separated by spaces

    val queryWords = query.split(" ")
    val resultLines = mutableListOf<Int>()

    // Initialize resultLines with lines containing the first word in the query
    resultLines.addAll(invertedIndex[queryWords.first()] ?: emptyList())

    // Iterate through other words in the query and retain lines that contain all query words
    for (word in queryWords.drop(1)) {
        resultLines.retainAll(invertedIndex[word] ?: emptyList())
    }

    // Print lines containing all query words
    println("Lines containing all words in the query:")
    for (line in resultLines) {
        println("Line $line") // Replace with your actual output format
    }
}
