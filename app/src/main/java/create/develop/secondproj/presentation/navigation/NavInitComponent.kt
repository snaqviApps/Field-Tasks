package create.develop.secondproj.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import create.develop.secondproj.presentation.screen.UserDetailScreen
import create.develop.secondproj.presentation.screen.UserInputScreen

@Composable
fun NavInitComponent(
    modifier: Modifier = Modifier
) {
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