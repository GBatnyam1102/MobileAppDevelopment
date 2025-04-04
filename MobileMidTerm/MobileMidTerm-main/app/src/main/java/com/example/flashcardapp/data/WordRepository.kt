package com.example.flashcardapp.data

import kotlinx.coroutines.flow.Flow

class WordRepository(private val WordDatabase: WordDatabase) {
    suspend fun addWord(wordPair: WordPair) {
        WordDatabase.wordPairDao().addWord(wordPair)
    }

    fun getAllWords(): Flow<List<WordPair>>{
        return WordDatabase.wordPairDao().getAllWords()
    }

    suspend fun deleteWord(wordPair: WordPair) {
        WordDatabase.wordPairDao().deleteWord(wordPair)
    }

    suspend fun updateWord(wordPair: WordPair) {
        WordDatabase.wordPairDao().updateWord(wordPair)
    }

}