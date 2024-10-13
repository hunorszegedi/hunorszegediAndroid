package main

fun main() {
    val itemRepository: ItemRepository = ItemRepository()
    val itemService: ItemService = ItemService(itemRepository)
    val itemController: ItemController = ItemController(itemService)

//    val items = itemRepository.getAll()
//    items.forEach{ println(it) }

//    println(itemService.selectRandomItems(3))

    var numberOfQuestions: String? = null

    while (true) {

        var inputInt = 0
        var validInput = false

        while (!validInput) {
            println("Num of questions(1-10)/Enter 'q' to quit:")
            numberOfQuestions = readLine()

            if (numberOfQuestions == "q") {
                return
            }

            try {
                if (numberOfQuestions != null) {
                    inputInt = numberOfQuestions.toInt()
                    if (inputInt < 11)
                        validInput = true
                }
            } catch (e: NumberFormatException) {
                println("Please write a valid number")
            }
        }

        itemController.quiz(numberOfQuestions!!.toInt())

    }
}