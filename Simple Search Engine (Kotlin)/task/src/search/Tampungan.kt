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

fun main() {
    val color = findByName("Black")
    println(color.ordinal)
}