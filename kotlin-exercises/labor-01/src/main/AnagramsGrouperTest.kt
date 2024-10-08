package main

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class AnagramsGrouperTest {
    private val anagramGrouper = AnagramGrouper()

    @Test
    fun threeGroupsAllLowerCase() {
        val anagrams = anagramGrouper.groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tea", "ate")))
        assertTrue(anagrams.contains(listOf("tan", "nat")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun threeGroupsSomeUpperCase() {
        val anagrams = anagramGrouper.groupAnagrams(arrayOf("eat", "tEa", "Tan", "atE", "NAT", "bat"))
        assertEquals(3, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat", "tEa", "atE")))
        assertTrue(anagrams.contains(listOf("Tan", "NAT")))
        assertTrue(anagrams.contains(listOf("bat")))
    }

    @Test
    fun validOneGroup() {
        val anagrams = anagramGrouper.groupAnagrams(arrayOf("eat"))
        assertEquals(1, anagrams.size)
        assertTrue(anagrams.contains(listOf("eat")))
    }

    @Test
    fun noGroup() {
        val anagrams = anagramGrouper.groupAnagrams(arrayOf())
        assertEquals(0, anagrams.size)
    }
}
