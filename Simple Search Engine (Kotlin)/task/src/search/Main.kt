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
        if(File(fileName).exists()) {
            val peopleList = File(fileName).readLines().toMutableList()
            val menuArray = intArrayOf(0,1,2)
            printMenu()
            var menuChosen = readln().toInt()

            while (menuChosen != 0){
                if (menuArray.contains(menuChosen)) {
                    if (menuChosen == 1) {
                        println("Enter a name or email to search all suitable people.")
                        val peopleString = readln()
                        searchPeople(peopleList,peopleString)

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