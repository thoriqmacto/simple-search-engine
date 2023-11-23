package search

fun main() {
//    val sentence = readln()
//    val word = readln()
//    val listWord = sentence.split(" ")
//
//    var wordPosition = 0
//
//    for (w in listWord){
//        if (word == w){
//            wordPosition = listWord.indexOf(w) + 1
//            break
//        }
//    }
//
//    println(if (wordPosition != 0) wordPosition else "Not found")
    println("Enter the number of people:")
    val numOfPeople = readln().toInt()
    println("Enter all people:")
    val peopleList = mutableListOf<String>()
    repeat(numOfPeople){
        val addedPeople = readln()
        peopleList.add(addedPeople)
    }
//    printAllPeople(peopleList)
    println("Enter the number of search queries:")
    val numOfQueries = readln().toInt()
    repeat(numOfQueries){
        println("Enter data to search people:")
        val peopleString = readln()
        searchPeople(peopleList,peopleString)
    }
}

fun printAllPeople(peopleList:MutableList<String>){
    peopleList.forEach { println(it) }
}

fun searchPeople(peopleList: MutableList<String>, stringToFind:String){
    val foundName = mutableListOf<String>()

    peopleList.forEach {
        person ->
        if (person.contains(stringToFind, ignoreCase = true)){
            foundName.add(person)
        }
    }

    if(foundName.isNotEmpty()){
        println("People found:")
        printAllPeople(foundName)
    }else{
        println{"No matching people found."}
    }
}