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
import create.develop.secondproj.data.loggin.remote.UserDetails
import create.develop.secondproj.presentation.UserInputViewModel
import create.develop.secondproj.presentation.navigation.UiEvent
import create.develop.secondproj.state.UserInfoState

@Composable
fun UserInputScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInputViewModel = viewModel(),
    onNavigateToDetail: (userDetails: UserDetails) -> Unit,
) {
    // Collect one-time navigation events from the ViewModel
    LaunchedEffect(viewModel.uiEvent) {
        viewModel.uiEvent.collect { event : UiEvent ->
            when (event) {
                is UiEvent.NavigateToDetail -> {
                    onNavigateToDetail(
                        event.userDetails
                    )
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
            LoginScreen(
                modifier = modifier,
                state = currentState,
                onInputChangedUserName = { uName ->
                    viewModel.onInputNameChanged(uName)
                },
                onInputChangedPassword = { pWord ->
                    viewModel.onInputPasswordChanged(pWord)
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
                Text(text = currentState.eMessage)
            }
        }
    }
}
