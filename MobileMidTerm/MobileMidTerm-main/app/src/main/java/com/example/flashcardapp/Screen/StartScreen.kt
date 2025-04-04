import android.content.Context
import android.content.pm.ModuleInfo
import android.view.RoundedCorner
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashcardapp.R
import com.example.flashcardapp.data.SettingsDataStore
import com.example.flashcardapp.data.WordPair
import com.example.flashcardapp.model.WordViewModel
import kotlin.collections.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    viewModel: WordViewModel,
    context: Context,
    onFixAndUpdateButtonClick: () -> Unit
) {
    val settingsDataStore = remember { SettingsDataStore(context) }
    var menuExpanded by remember { mutableStateOf(false) }
    var index by remember { mutableStateOf(0) }
    var selectedWord by remember { mutableStateOf<WordPair?>(null) }
    var selectedOption by remember { mutableStateOf("Хоёуланг нь ил харуулах") }
    val wordList by remember { viewModel.words }.collectAsState(initial = emptyList())
    var inputForeign by remember { mutableStateOf("") }
    var inputMongolian by remember { mutableStateOf("") }

    LaunchedEffect(wordList) {
        wordList.forEach {

        }
    }
    // DataStore-оос тохиргооны утга унших
    LaunchedEffect(Unit) {
        settingsDataStore.selectedOptionFlow.collect { option ->
            selectedOption = option
        }
    }

    Scaffold(
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                val word = listOf(inputMongolian, inputMongolian)
                Column(
                    modifier = Modifier.padding(top = 25.dp)
                ) {
                    when (selectedOption) {
                        "Гадаад үгийг ил харуулах" -> {
                            OneTextInput(word, false, false)
                            OneTextInput(word, true, true)
                        }
                        "Монгол үгийг ил харуулах" -> {
                            OneTextInput(word, true, false)
                            OneTextInput(word, false, true)
                        }
                        else -> {
                            OneTextInput(word, false, false)
                            OneTextInput(word, false, true)
                        }
                    }
                }
                Row (modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxHeight(0.5f))
                {
                    Button(
                        onClick = {
                            if(inputForeign.isNotEmpty() && inputMongolian.isNotEmpty()) {
                                viewModel.addWord(WordPair(0, inputMongolian, inputForeign))
                                inputMongolian = ""
                                inputForeign = ""
                            }
                            onFixAndUpdateButtonClick()},
                        colors = ButtonDefaults.buttonColors(Color(0xFFab47bc))
                        ){
                        Text(
                            text = "Нэмэх"
                        )
                    }
                    Button(
                        onClick = {
                            selectedWord?.let {
                                val word = WordPair(it.id, inputMongolian, inputForeign)
                                viewModel.updateWord(word)
                                inputMongolian = ""
                                inputForeign = ""
                                selectedWord = null
                            }
                            onFixAndUpdateButtonClick()},
                        colors = ButtonDefaults.buttonColors(Color(0xFFab47bc))
                        ){
                        Text(
                            text = "Шинэчлэх"
                        )
                    }
                    Button(
                        onClick = {
                            selectedWord?.let {
                                viewModel.deleteWord(it)
                                inputForeign = ""
                                inputMongolian = ""
                                selectedWord = null
                                index = if (wordList.isEmpty()) 0 else (index - 1).coerceAtLeast(0)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFFab47bc))
                        ){
                        Text(
                            text = "устгах"
                        )
                    }
                }
                Row (
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center
                )

                {
                    Button(
                        onClick = {
                            if (index < wordList.size - 1) {
                                index++
                                val word = wordList[index]
                                inputForeign = word.english
                                inputMongolian = word.mongolian
                                selectedWord = word
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFFab47bc))
                        ){
                        Text(
                            text = "Дараа"
                        )
                    }
                    Button(
                        onClick = {
                            if (index > 0) {
                                index--
                                val word = wordList[index]
                                inputForeign = word.english
                                inputMongolian = word.mongolian
                                selectedWord = word
                            }

                        },
                        colors = ButtonDefaults.buttonColors(Color(0xFFab47bc))
                        ){
                        Text(
                            text = "Өмнөх"
                        )
                    }
                }
            }
        }
    )
}
//hidden logic zasna
@Composable
fun OneTextInput(utguud: List<String>, isHiddenInit: Boolean, ismongolia: Boolean) {
    var isHidden by remember { mutableStateOf(isHiddenInit) } // Эхлээд нуусан эсэхийг хянах
    val text = if (ismongolia) utguud[0] else utguud[1] // Монгол эсвэл Англи үг

    OutlinedTextField(
        value = if (isHidden) "" else text, // Хэрэв нуусан бол хоосон байна
        onValueChange = {},
        label = { Text(if (ismongolia) "MN" else "EN") },
        placeholder = { Text(text) }, // Нуусан үед placeholder дээр үг харагдана
        shape = RoundedCornerShape(12.dp),
        readOnly = true,
    )
}


