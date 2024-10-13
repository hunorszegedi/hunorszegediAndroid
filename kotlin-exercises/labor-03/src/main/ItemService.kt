package main

class ItemService (private val itemRepository: ItemRepository){
    val items = itemRepository.getAll()

    fun selectRandomItems(numberOfQuestions: Int): List<Item>{
        if (numberOfQuestions > items.size) {
            throw IllegalArgumentException("Invalid number of list items")
        }

        val randomItems = mutableListOf<Item>()

        while (randomItems.size < numberOfQuestions){
            val randItem = itemRepository.randomItem()
            if(randItem !in randomItems)
                randomItems.add((randItem))
        }
        return randomItems
    }
}
