package create.develop.secondproj.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import create.develop.secondproj.data.GetUserInputService
import create.develop.secondproj.domain.GetUserInput
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserInputViewModel(
    private val getUserInputService: GetUserInput = GetUserInputService()
    ) : ViewModel() {

    private val _userInputState = MutableStateFlow<UserInfoState>(UserInfoState())
    val userInputState = _userInputState.asStateFlow()

    fun onInputIdChanged(userId: String) {
        _userInputState.update { currentState ->
            currentState.copy(userInput = currentState.userInput.copy(id = userId))
        }
        Log.d("UserInputViewModel_Id", userId)
    }

    fun onInputNameChanged(name: String) {
        _userInputState.update { currentState ->
                currentState.copy(userInput = currentState.userInput.copy(name = name))
        }
        Log.d("UserInputViewModel_name", name)
    }

}