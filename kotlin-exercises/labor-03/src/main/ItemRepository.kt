package main

import java.io.File

class ItemRepository() {
    val items = mutableListOf<Item>()

    init {
        val data = File("C:\\Users\\Vivobook\\Desktop\\III. év\\android\\kotlin-exercises\\labor-03\\src\\romanEmpire.txt").useLines { it.toList() }

        for (i in data.indices step 6) {
            val question = data[i]
            val answers1 = data[i + 1]
            val answers2 = data[i + 2]
            val answers3 = data[i + 3]
            val answers4 = data[i + 4]
            val correct = data[i + 5].toInt()

            save(Item(listOf(answers1, answers2, answers3, answers4), correct, question))
        }
    }

    fun randomItem(): Item {
        val randomIndex = (0 until items.size).random()
        return items[randomIndex]
    }

    fun save(item: Item) {
        items.add(item)
    }

    fun size(): Int {
        return items.size
    }

    fun getAll(): List<Item>{
        return items
    }
}