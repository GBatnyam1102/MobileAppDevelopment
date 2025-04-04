package com.example.flashcardapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardapp.data.WordPair
import com.example.flashcardapp.data.WordRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository): ViewModel() {
    val words: Flow<List<WordPair>> = repository.getAllWords()

    fun addWord(wordPair: WordPair) {
        viewModelScope.launch {
            repository.addWord(wordPair)
        }
    }

    fun updateWord(wordPair: WordPair) {
        viewModelScope.launch {
            repository.updateWord(wordPair)
        }
    }

    fun deleteWord(wordPair: WordPair) {
        viewModelScope.launch {
            repository.deleteWord(wordPair)
        }
    }

}