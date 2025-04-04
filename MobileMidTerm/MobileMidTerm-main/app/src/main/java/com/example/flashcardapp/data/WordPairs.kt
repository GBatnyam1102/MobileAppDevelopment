package com.example.flashcardapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WordPair(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var mongolian : String,
    var english : String
)
