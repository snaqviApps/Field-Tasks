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
    onNavigateToDetail: (id: String, name: String) -> Unit,
) {
    val state: UserInfoState by viewModel.userInputState.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is UserInfoState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is UserInfoState.Success -> {
            UserInputProvider(
                modifier = modifier,
                state = currentState,
                onInputChangedID = { id ->
                    viewModel.onInputIdChanged(id)
                },
                onInputChangedName = { name ->
                    viewModel.onInputNameChanged(name)
                },
                onSubmit = {
                    onNavigateToDetail(currentState.userInput.id, currentState.userInput.name)
                }
            )
        }
        is UserInfoState.Error -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = currentState.message)
            }
        }
    }
}
