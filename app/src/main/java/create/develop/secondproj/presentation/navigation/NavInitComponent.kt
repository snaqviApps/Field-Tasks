package create.develop.secondproj.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import create.develop.secondproj.presentation.screen.UserDetailScreen
import create.develop.secondproj.presentation.screen.UserInputScreen

@Composable
fun NavInitComponent(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(Screen.Input)

    NavDisplay(
        backStack = backStack,
        contentAlignment = Alignment.Center,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            // Since key is of type NavKey, we use an exhaustive when with an else branch
            // to ensure a non-nullable NavEntry is returned.
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

                else -> error("Unknown key type: $key")
            }
        }
    )
}
