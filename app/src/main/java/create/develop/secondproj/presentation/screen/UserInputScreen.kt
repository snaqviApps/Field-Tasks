package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import create.develop.secondproj.presentation.UserInputViewModel
import create.develop.secondproj.state.UserInfoState

@Composable
fun UserInputScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInputViewModel = viewModel(),
    onNavigateToDetail: (userName: String, password: String) -> Unit,
) {
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
                    viewModel.onInputIdChanged(uName)
                },
                onInputChangedPassword = { pWord ->
                    viewModel.onInputNameChanged(pWord)
                },
                onSubmit = {

                    /**
                     * call POST request to login user using UserServices: loginUser(LoginRequest via viewModel )
                     * */
                    { 
                            // state.loginRequest.username.isNotEmpty() && state.loginRequest.password.isNotEmpty() {

                    }
                    onNavigateToDetail(currentState.loginRequest.username, currentState.loginRequest.password)
                }
            )
        }

        is UserInfoState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = currentState.message)
            }
        }
    }
}