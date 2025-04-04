package com.example.flashcardapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(WordPair: WordPair)

    @Update
    suspend fun updateWord(WordPair: WordPair)

    @Delete
    suspend fun deleteWord(WordPair: WordPair)

    @Query("SELECT * FROM WordPair")
    fun getAllWords(): Flow<List<WordPair>>

}