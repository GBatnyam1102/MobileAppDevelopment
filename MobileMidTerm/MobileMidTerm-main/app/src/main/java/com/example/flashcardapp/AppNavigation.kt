package com.example.flashcardapp

import StartScreen
import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Constraints
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.flashcardapp.Screen.FixAndUpdateScreen
import com.example.flashcardapp.Screen.RadioButtonScreen
import com.example.flashcardapp.model.WordViewModel

enum class FlashCardScreens(@StringRes val title: Int) {
    Start(R.string.start),
    Settings(R.string.settings),
    FixAndUpdate(R.string.fixandupdate)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardBar(
    navController: NavController
) {
    var menuExpanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.flash_card_app),
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color(0xFFab47bc),
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            IconButton(onClick = { menuExpanded = !menuExpanded }) {
                Icon(Icons.Default.MoreVert, contentDescription = "Menu")
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Тохиргоо") },
                    onClick = {
                        navController.navigate(FlashCardScreens.Settings.name)
                        menuExpanded = false
                    }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation(
    viewModel: WordViewModel
) {

    val navController = rememberNavController()

    Scaffold(
        topBar = { FlashCardBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = FlashCardScreens.Start.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(FlashCardScreens.Start.name) {
                val context = LocalContext.current
                StartScreen(
                    viewModel = viewModel,
                    context = context,
                    onFixAndUpdateButtonClick = {
                        navController.navigate(FlashCardScreens.FixAndUpdate.name)
                    }
                )
            }
            composable(FlashCardScreens.FixAndUpdate.name) {
                FixAndUpdateScreen(
                    viewModel = viewModel,
                    onInsertAndCancel = {
                        navController.navigate(FlashCardScreens.Start.name)
                    }
                )
            }
            composable(FlashCardScreens.Settings.name) {
                val context = LocalContext.current
                RadioButtonScreen(
                    context = context,
                    onBackAndSaveButtonClick = {
                        navController.navigate(FlashCardScreens.Start.name)
                    }
                )
            }
        }
    }

}

