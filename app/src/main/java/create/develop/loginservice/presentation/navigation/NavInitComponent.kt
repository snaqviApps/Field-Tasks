package create.develop.loginservice.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import create.develop.loginservice.presentation.screen.UserDetailScreen
import create.develop.loginservice.presentation.screen.UserInputScreen

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
                        onNavigateToDetail = { userDetails ->
                            backStack.add(
                                Screen.Detail(userDetails)
                            )
                        }
                    )
                }

                is Screen.Detail -> NavEntry(key) {
                    UserDetailScreen(
                        modifier = modifier,
                        key.userDetail)
                }

                else -> error("Unknown key type: $key")
            }
        }
    )
}
