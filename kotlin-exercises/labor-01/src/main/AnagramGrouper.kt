package main

class AnagramGrouper {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        return strs.groupBy { it.lowercase().toCharArray().sorted() }
            .map { it.value }
    }
}