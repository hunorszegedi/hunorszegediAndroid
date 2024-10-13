package main;

import java.io.File

public class HashSetDictionary(filePath: String) : IDictionary{
    private val wordHashSet = hashSetOf<String>()

    init {
        val file = File(filePath)
        if(file.exists() && file.isFile){
            file.forEachLine {
                wordHashSet.add(it.trim())
            }
        }
        else{
            println("there's no such file or directory")
        }
    }

    override fun add(word: String): Boolean {
        return wordHashSet.add(word)
    }

    override fun find(word: String): Boolean {
        return wordHashSet.contains(word)
    }

    override fun size(): Int {
        return wordHashSet.size
    }
}
