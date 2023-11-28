package search
import java.io.File

fun main(args:Array<String>) {
    if (args[0] == "--data"){
        val fileName = args[1]
        val file = File(fileName)
        if(file.exists()) {
            val peopleList = file.readLines().toMutableList()
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

            // invertedIndex.forEach { println(it) }

            val menuArray = intArrayOf(0,1,2)
            printMenu()
            var menuChosen = readln().toInt()

            while (menuChosen != 0){
                if (menuArray.contains(menuChosen)) {
                    if (menuChosen == 1) {
                        println("Select a matching strategy: ALL, ANY, NONE")
                        var strategyInput = readln().uppercase()

                        var strategy:Strategy = when(strategyInput){
                            "ALL" -> Strategy.ALL
                            "ANY" -> Strategy.ANY
                            "NONE" -> Strategy.NONE
                            else -> {Strategy.NULL}
                        }

                        while(strategy == Strategy.NULL){
                            println("Your strategy not listed. Please choose again.")
                            println("Select a matching strategy: ALL, ANY, NONE")
                            strategyInput = readln().uppercase()
                            strategy = when(strategyInput){
                                "ALL" -> Strategy.ALL
                                "ANY" -> Strategy.ANY
                                "NONE" -> Strategy.NONE
                                else -> {Strategy.NULL}
                            }
                        }

                        println("Enter a name or email to search all suitable people.")
                        val searchString = readln().split(" ").map{ it.lowercase() }.toSet()
                        val resultLine = searchResult(invertedIndex,strategy,searchString)
                        if(resultLine.size != 0){
                            resultLine.forEach { println(peopleList[it]) }
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

enum class Strategy {
    ALL,ANY,NONE,NULL;

//    fun isValid(strInput:String): Boolean {
//        for (enum in Strategy.values()){
//            if(strInput.uppercase() == enum.name) return true
//        }
//        return false
//    }
}

fun searchResult(inputInvertedIndex:MutableMap<String,MutableList<Int>>, inputStrategy:Strategy,
                 searchString:Set<String>):MutableList<Int>{
    var resultLine = mutableListOf<Int>()

    when (inputStrategy.name) {
        "ALL" -> {
            resultLine.addAll(inputInvertedIndex[searchString.first()]?: emptyList())
            for (str in searchString.drop(1)){
                resultLine.retainAll(inputInvertedIndex[str]?: emptyList())
            }
        }
        "ANY" -> {
            searchString.forEach { it1 ->
                inputInvertedIndex[it1]?.forEach { it2 ->
                    resultLine.add(it2)
                }
            }
        }
        "NONE" -> {
            val excludedValues = mutableListOf<Int>()
            searchString.forEach { str ->
                inputInvertedIndex[str]?.forEach {excludedValues.add(it)}
            }

            val excludedInvertedIndex = inputInvertedIndex.filterValues { lines ->
                lines.any { it !in excludedValues}
            }
            resultLine = excludedInvertedIndex.values.flatten().distinct().toMutableList()
//            println(inputInvertedIndex)
//            println(excludedValues)
//            println("Search String: $searchString")
//            println(resultLine)
        }
    }

    when(val totalFound = resultLine.size){
        0 -> println("No matching people found.")
        1 -> println("$totalFound person found:")
        else -> println("$totalFound persons found:")
    }

    return resultLine
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