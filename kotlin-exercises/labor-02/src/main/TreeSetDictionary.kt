package main

import java.io.File
import java.util.TreeSet

class TreeSetDictionary(filePath: String) : IDictionary {
    private val wordTreeSet = TreeSet<String>()

    init {
        val file = File(filePath)
        if(file.exists() && file.isFile){
            file.forEachLine {
                wordTreeSet.add(it.trim())
            }
        }
        else{
            println("there's no such file or directory")
        }
    }

    override fun add(word: String): Boolean {
        return wordTreeSet.add(word)
    }

    override fun find(word: String): Boolean {
        return wordTreeSet.contains(word)
    }

    override fun size(): Int {
        return wordTreeSet.size
    }
}