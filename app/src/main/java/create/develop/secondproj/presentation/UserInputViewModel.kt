package create.develop.secondproj.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import create.develop.secondproj.data.GetUserInputService
import create.develop.secondproj.domain.GetUserInput
import create.develop.secondproj.data.logging.UserInput
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserInputViewModel(
    private val getUserInputService: GetUserInput = GetUserInputService()           // for future implementation
) : ViewModel() {

    private val _userInputState = MutableStateFlow<UserInfoState>(UserInfoState.Success(UserInput()))
    val userInputState = _userInputState.asStateFlow()

    fun onInputIdChanged(userId: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(userInput = currentState.userInput.copy(id = userId))
            } else {
                currentState
            }
        }
        Log.d("UserInputViewModel_Id", userId)
    }

    fun onInputNameChanged(name: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(userInput = currentState.userInput.copy(name = name))
            } else {
                currentState
            }
        }
        Log.d("UserInputViewModel_name", name)
    }
}
