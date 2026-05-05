package create.develop.secondproj.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import create.develop.secondproj.data.UserServices
import create.develop.secondproj.data.loggin.local.LoginRequest
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.data.loggin.remote.toUserProfile
import create.develop.secondproj.domain.UserApi
import create.develop.secondproj.presentation.navigation.UiEvent
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Response

class UserInputViewModel(
    private val useServices: UserApi = UserServices()
) : ViewModel() {

    private var response: Response<LoginResponse>? = null
    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _userInputState = MutableStateFlow<UserInfoState>(UserInfoState.Success(LoginRequest()))
    val userInputState = _userInputState.asStateFlow()

    fun login() {
        viewModelScope.launch {
            try {
                val currentState = _userInputState.value
                if (currentState is UserInfoState.Success) {
                    val userName = currentState.loginRequest.username
                    val password = currentState.loginRequest.password

                    val loginResponse = useServices.loginUser(LoginRequest(userName, password))
                    response = loginResponse

                    if (loginResponse.isSuccessful) {
                        val token = loginResponse.body()?.accessToken
                        Log.d("UserInputViewModel_Success", token ?: "Token is null")

                        if (!token.isNullOrEmpty()) {
                            val infoResponse = useServices.getUserInfo("Bearer $token")
                            if (infoResponse.isSuccessful) {
                                val userInfo = infoResponse.body()?.toUserProfile()
                                Log.d("UserInputViewModel_UserInfo", userInfo?.email ?: "Username email is empty")
                            } else {
                                Log.e("UserInputViewModel_Error", "User info fetch failed: ${infoResponse.code()}")
                            }
                        }

                        _uiEvent.emit(UiEvent.NavigateToDetail(userName, password))
                    } else {
                        Log.e("UserInputViewModel_Error", "Login failed: ${loginResponse.code()}")
                    }
                }

            } catch (e: Exception) {
                Log.e("UserInputViewModel_Exception", "Login operation failed", e)
            }
        }
    }

    fun onInputIdChanged(userName: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(loginRequest = currentState.loginRequest.copy(username = userName))
            } else {
                currentState
            }
        }
    }

    fun onInputNameChanged(password: String) {
        _userInputState.update { currentState : UserInfoState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(loginRequest = currentState.loginRequest.copy(password = password))
            } else {
                currentState
            }
        }
    }
}
