package main

import java.io.File

class ListDictionary(filePath: String) : IDictionary {
    private val wordList = mutableListOf<String>()

    init {
        val file = File(filePath)
        if(file.exists() && file.isFile){
            file.forEachLine {
                add(it.trim())
            }
        }
        else{
            println("there's no such file or directory")
        }
    }
    override fun add(word: String): Boolean {
        return wordList.add(word)
    }

    override fun find(word: String): Boolean {
        return wordList.contains(word)
    }

    override fun size(): Int {
        return wordList.size
    }


}