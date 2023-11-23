fun main() {
    val studentsMarks = mutableMapOf<String, Int>()

    var entryKey = readln()

    while (entryKey != "stop"){
        val entryVal = readln().toInt()

        if (!studentsMarks.containsKey(entryKey)){
            studentsMarks[entryKey] = entryVal
        }
        entryKey= readln()
    }
    println(studentsMarks)
}