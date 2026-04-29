package create.develop.secondproj.presentation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import create.develop.secondproj.presentation.UserInputViewModel
import create.develop.secondproj.state.UserInfoState

@Composable
fun UserInputScreen(
    modifier: Modifier = Modifier,
    viewModel: UserInputViewModel = viewModel()
) {
    val state: UserInfoState by viewModel.userInputState.collectAsStateWithLifecycle()

    UserInputProvider(
        modifier = modifier,
        state = state,
        onInputChangedID = { id ->
            viewModel.onInputIdChanged(id)
        },
        onInputChangedName = { name ->
            viewModel.onInputNameChanged(name)
        },
        onSubmit = {
            onNavigateToDetail(state.userInput.id, state.userInput.name)
        }
    )
}

fun onNavigateToDetail(id: String, name: String) {
    TODO("Not yet implemented")
}
