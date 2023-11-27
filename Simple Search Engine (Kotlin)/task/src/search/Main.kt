package search
import java.io.File

fun main(args:Array<String>) {
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
//    println("Enter the number of people:")
//    val numOfPeople = readln().toInt()
//    println("Enter all people:")
//    val peopleList = mutableListOf<String>()
//    repeat(numOfPeople){
//        val addedPeople = readln()
//        peopleList.add(addedPeople)
//    }
//    printAllPeople(peopleList)
    if (args[0] == "--data"){
        val fileName = args[1]
        val file = File(fileName)
        if(file.exists()) {
            val peopleList = file.readLines().toMutableList()
//            val peopleList = mutableListOf<String>()

            // for text containing ":"
//            file.forEachLine{
//                val parts = it.split(":")
//                peopleList.add(parts[1])
//            }



//            peopleList.forEach { println() }

            val invertedIndex = mutableMapOf<String,MutableList<Int>>()

            peopleList.forEach { list ->
                val words = list.split(" ").map{it.lowercase()}.toSet()
                words.forEach { word ->
                    if(invertedIndex.containsKey(word)){
                        invertedIndex[word]?.add(peopleList.indexOf(list))
                    }else {
                        invertedIndex[word] = mutableListOf(peopleList.indexOf(list))
                    }
                }
            }

//            invertedIndex.forEach { println(it) }

            val menuArray = intArrayOf(0,1,2)
            printMenu()
            var menuChosen = readln().toInt()

            while (menuChosen != 0){
                if (menuArray.contains(menuChosen)) {
                    if (menuChosen == 1) {
                        println("Enter a name or email to search all suitable people.")
                        val peopleString = readln().lowercase()
//                        searchPeople(peopleList,peopleString)
                        val totalFound = invertedIndex[peopleString]?.size?:0

                        when(totalFound){
                            0 -> println("No matching people found.")
                            1 -> println("$totalFound person found:")
                            else -> println("$totalFound persons found:")
                        }

                        if (totalFound >0) {
                            invertedIndex[peopleString]?.forEach { println(peopleList[it]) }
                        }

                        printMenu()
                        menuChosen= readln().toInt()
                    }else if(menuChosen == 2){
                        printAllPeople(peopleList)

                        printMenu()
                        menuChosen= readln().toInt()
                    }
                }else{
                    println("Incorrect option! Try again.")
                    printMenu()
                    menuChosen = readln().toInt()
                }
            }
            println("Bye!")
        }else{
            println("No such file")
        }
    }
}

fun printAllPeople(peopleList:MutableList<String>){
    println("=== List of people ===")
    peopleList.forEach { println(it) }
}

fun printMenu(){
    println("=== Menu ===")
    println("1. Find a person")
    println("2. Print all people")
    println("0. Exit")
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
//        println("People found:")
        printAllPeople(foundName)
    }else{
        println{"No matching people found."}
    }
}