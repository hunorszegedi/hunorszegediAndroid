class ListDictionary : IDictionary {
    private val words = mutableListOf<String>()

    override fun add(word: String): Boolean {
        return if (word in words) {
            false
        } else {
            words.add(word)
            true
        }
    }

    override fun find(word: String): Boolean {
        return word in words
    }

    override fun size(): Int {
        return words.size
    }
}
