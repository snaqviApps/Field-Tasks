package create.develop.secondproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import create.develop.secondproj.presentation.Screen
import create.develop.secondproj.presentation.screen.UserDetailScreen
import create.develop.secondproj.presentation.screen.UserInputScreen
import create.develop.secondproj.ui.theme.SecondProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondProjTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
                    val backStack = remember { mutableStateListOf<Any>(Screen.Input) }

                    NavDisplay(
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryProvider = { key ->
                            when (key) {
                                is Screen.Input -> NavEntry(key) {
                                    UserInputScreen(
                                        modifier = modifier,
                                        onNavigateToDetail = { id, name ->
                                            backStack.add(Screen.Detail(id, name))
                                        }
                                    )
                                }
                                is Screen.Detail -> NavEntry(key) {
                                    UserDetailScreen(
                                        id = key.id,
                                        name = key.name,
                                        modifier = modifier
                                    )
                                }
                                else -> NavEntry(Unit) { Text("Unknown route") }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserInputScreenPreview() {
    SecondProjTheme {
        UserInputScreen(onNavigateToDetail = { _, _ -> })
    }
}
