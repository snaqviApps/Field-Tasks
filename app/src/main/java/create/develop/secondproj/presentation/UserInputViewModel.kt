package create.develop.secondproj.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import create.develop.secondproj.data.UserServices
import create.develop.secondproj.data.loggin.local.LoginRequest
import create.develop.secondproj.domain.UserApi
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserInputViewModel(
    private val useServices: UserApi = UserServices()           // for future implementation
) : ViewModel() {

    private val _userInputState = MutableStateFlow<UserInfoState>(UserInfoState.Success(LoginRequest()))
    val userInputState = _userInputState.asStateFlow()

    fun onInputIdChanged(userName: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(loginRequest = currentState.loginRequest.copy(username = userName))
            } else {
                currentState
            }
        }
        Log.d("UserInputViewModel_Id", userName)
    }

    fun onInputNameChanged(password: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(loginRequest = currentState.loginRequest.copy(password = password))
            } else {
                currentState
            }
        }
        Log.d("UserInputViewModel_password", password)
    }
}
