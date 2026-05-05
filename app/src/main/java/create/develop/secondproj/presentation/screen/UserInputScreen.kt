package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import create.develop.secondproj.presentation.UserInputViewModel
import create.develop.secondproj.presentation.navigation.UiEvent
import create.develop.secondproj.state.UserInfoState

@Composable
fun UserInputScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInputViewModel = viewModel(),
    onNavigateToDetail: (userName: String, password: String) -> Unit,
) {
    // Collect one-time navigation events from the ViewModel
    // This fixes the logic: navigation only happens when login succeeds,
    // not automatically when the screen opens in a Success state.
    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event ->
            when (event) {
//                is UserInputViewModel.UiEvent.NavigateToDetail -> {
                is UiEvent.NavigateToDetail ->  {
                    onNavigateToDetail(event.username, event.password)
                }
            }
        }
    }

    val state: UserInfoState by viewModel.userInputState.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is UserInfoState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is UserInfoState.Success -> {
            // LaunchedEffect removed from here to prevent infinite/automatic navigation
            LoginScreen(
                modifier = modifier,
                state = currentState,
                onInputChangedUserName = { uName ->
                    viewModel.onInputIdChanged(uName)
                },
                onInputChangedPassword = { pWord ->
                    viewModel.onInputNameChanged(pWord)
                },
                onSubmit = {
                    viewModel.login()
                }
            )
        }

        is UserInfoState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = currentState.message)
            }
        }
    }
}
