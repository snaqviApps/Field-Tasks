package create.develop.secondproj.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import create.develop.secondproj.data.loggin.remote.UserDetails
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
                        onNavigateToDetail = { email, firstName, gender, image, lastName, birthDate, bloodGroup, business, address ->
                            backStack.add(
                                Screen.Detail(
                                    UserDetails(
                                        email = email,
                                        firstName = firstName,
                                        gender = gender,
                                        image = image,
                                        lastName = lastName,
                                        birthDate = birthDate,
                                        bloodGroup = bloodGroup,
                                        business = business,
                                        address = address
                                    )
                                )
                            )

                        }
                    )
                }

                is Screen.Detail -> NavEntry(key) {
                    UserDetailScreen(
                        firstName = key.userDetail.firstName,
                        gender = key.userDetail.gender,
                        image = key.userDetail.image,
                        lastName = key.userDetail.lastName,
                        birthDate = key.userDetail.birthDate,
                        bloodGroup = key.userDetail.bloodGroup,
                        address = key.userDetail.address,
                        business = key.userDetail.business
                    )
                }

                else -> error("Unknown key type: $key")
            }
        }
    )
}
