package main

import java.util.IllegalFormatException

class ItemController(private val itemService: ItemService) {

    fun quiz(numberOfQuestions: Int) {
        val selectedItems = itemService.selectRandomItems(numberOfQuestions)
        var correctAnswers = 0

        for (it in selectedItems) {
            println(it.question)
            for ((i, ans) in it.answers.withIndex()) {
                println("${i + 1} $ans")
            }

            var inputInt = 0
            var validInput = false

            while (!validInput) {
                println("Enter the number of the correct answer:")
                val input: String? = readLine()

                try {
                    if (input != null) {
                        inputInt = input.toInt()
                        if(inputInt < 5)
                            validInput = true
                    }
                } catch (e: NumberFormatException) {
                    println("Please write a valid number")
                }
            }

            if (inputInt == it.correct) {
                println("Your answer is correct")
                correctAnswers++
            } else {
                println("Correct Answer: ${it.answers[it.correct-1]}")
            }
            println()
        }

        println("Correct answers/Total number of answers: ${correctAnswers}/${numberOfQuestions}")
        println()
    }
}
