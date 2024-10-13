package main

object DictionaryProvider {
    fun createDictionary(type: DictionaryType, path: String): IDictionary {
        return when (type) {
            DictionaryType.TREE_SET -> TreeSetDictionary(path)
            DictionaryType.ARRAY_LIST -> ListDictionary(path)
            DictionaryType.HASH_SET -> HashSetDictionary(path)
        }
    }
}