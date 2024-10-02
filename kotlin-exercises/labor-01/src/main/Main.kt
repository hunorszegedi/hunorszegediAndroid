package main

fun addsTwoValues(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun isPrime(number: Int): Boolean {
    if (number <= 1) {
        return false
    }
    for (i in 2 until number) {
        if (number % i == 0) {
            return false
        }
    }
    return true
}

fun main() {
    //1.
    println("Exercise number 1:")
    val number1 = readln().toInt()
    val number2 = readln().toInt()
    val sum = addsTwoValues(number1, number2)
    println("Sum of the $number1 and $number2 is $sum")
//    print(addsTwoValues(number1, number2))

    //2.
    //a.
    println("Exercise number 2 a.:")
    val daysOfWeek = listOf(
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday",
        "Sunday"
    )
    for (day in daysOfWeek) {
        println(day)
    }
    //alternativa
    val mutDaysOfWeek = mutableListOf<String>()
    var index = 0
    while (index < 7) {
        val day = readln()
        mutDaysOfWeek.add(day)
        println(mutDaysOfWeek[index])
        index++
    }
    for (day in mutDaysOfWeek) {
        println(day)
    }
    //b.
    println("Exercise number 2 b.:")
    daysOfWeek
        .filter { it.startsWith("T") }
        .forEach { println(it) }
    //c.
    println("Exercise number 2 c.:")
    daysOfWeek
        .filter { it.contains("e") }
        .forEach { println(it) }
    //d.
    println("Exercise number 2 d.:")
    daysOfWeek
        .filter { it.length == 6 }
        .forEach { println(it) }

    //3.
    println("Exercise number 3:")
    println("Give me a range:")
    val range = readln().toInt()
    for(i in 1 until range){
        if(isPrime(i)){
            println("$i is Prime")
        }
    }

    //4.
    var toCodeString = readln()
}