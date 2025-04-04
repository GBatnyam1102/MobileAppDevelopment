package com.example.flashcardapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [WordPair::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase(){
    abstract fun wordPairDao(): WordDao

    companion object{
        @Volatile
        private var Instance: WordDatabase? = null

        fun getDatabase(context: Context): WordDatabase{
            return Instance ?: synchronized (this) {
                val instance = Room.databaseBuilder(
                    context, WordDatabase::class.java, "Word_database"
                ).build()
                Instance = instance
                instance
            }
        }
    }
}