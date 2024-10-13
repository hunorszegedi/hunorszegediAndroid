package main

import kotlin.random.Random

enum class DictionaryType {
    ARRAY_LIST,
    TREE_SET,
    HASH_SET
}

fun String.returnMonogram(): String {
    val splitted = this.split(" ");

    val monogram = splitted.map{it.first().toUpperCase()};

    return monogram.joinToString ("")
}

fun List<String>.joinWithSeparator(separator: String) = this.joinToString(separator)

fun List<String>.longestStr() = this.maxByOrNull { it.length }

fun main(){
    //feladat 1

//    val dict: IDictionary = DictionaryProvider.createDictionary(DictionaryType.HASH_SET, "words.txt")
//    println("Number of words: ${dict.size()}")
//    var word: String?
//    while(true){
//        print("What to find? ")
//        word = readLine()
//        if( word.equals("quit")){
//            break
//        }
//        println("Result: ${word?.let { dict.find(it) }}")
//    }

    //feladat2

//    val fullName = "John Smith";
//    val monogram = fullName.returnMonogram()
//    println(monogram)
//
//    val list = listOf("apple", "pear", "melon")
//    val separator = "#"
//    val res = list.joinWithSeparator(separator)
//    println(res)
//
//    val list1 = listOf("apple", "pear", "strawberry", "melon")
//    val longest = list1.longestStr()
//    println(longest)

    //feladat3

    val currentDate = Date()
    println("Year: ${currentDate.year}, Month: ${currentDate.month}, Day: ${currentDate.day}")

    val date1 = Date(2024, 2, 29) // Leap
    val date2 = Date(2023, 2, 28) // Nem leap

    println("${date1.year} is a leap year: ${date1.isLeapYear()}")
    println("${date2.year} is a leap year: ${date2.isLeapYear()}")

    val date3 = Date(2024, 2, 29)   //ez azert lesz false, mert a jelenlegi evnel nagyobbat is ki fogja szurni
    val date4 = Date(2023, 22, 20)

    println("Date3 is valid: ${date3.isValidDate()}")
    println("Date4 is valid: ${date4.isValidDate()}")

    val validDates = mutableListOf<Date>()
    val random = Random.Default

    while (validDates.size < 10) {
        val year = random.nextInt(2100)
        val month = random.nextInt(15) + 1
        val day = random.nextInt(31) + 1

        val date = Date(year, month, day)
        if (date.isValidDate()) {
            validDates.add(date)
        } else {
            println("Invalid date: $date")
        }
    }

    println("Valid Dates:")
    validDates.forEach{println(it)}

//    validDates.sort()
//
//    println("Sorted Valid Dates:")
//    validDates.forEach {
//        println(it)
//    }

    val reversedDates = validDates.reversed()

    println("Reversed Sorted Valid Dates:")
    reversedDates.forEach {
        println(it)
    }

    validDates.sortWith(DateComparator())

    println("Custom Sorted Valid Dates:")
    validDates.forEach {
        println(it)
    }
}
