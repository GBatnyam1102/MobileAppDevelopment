package com.example.flashcardapp

import StartScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.flashcardapp.data.WordDatabase
import com.example.flashcardapp.data.WordRepository
import com.example.flashcardapp.model.WordViewModel
import com.example.flashcardapp.ui.theme.FlashCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashCardAppTheme {
                val databaseOne = WordDatabase.getDatabase(LocalContext.current)
                val repositoryOne = WordRepository(databaseOne)
                val viewModelOne: WordViewModel by viewModels { WordViewModelFactory(repositoryOne) }
                AppNavigation(viewModelOne)
            }
        }
    }
}
