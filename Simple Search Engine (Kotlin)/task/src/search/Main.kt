package search

fun main() {
    val sentence = readln()
    val word = readln()
    val listWord = sentence.split(" ")

    var wordPosition = 0

    for (w in listWord){
        if (word == w){
            wordPosition = listWord.indexOf(w) + 1
            break
        }
    }

    println(if (wordPosition != 0) wordPosition else "Not found")
}
