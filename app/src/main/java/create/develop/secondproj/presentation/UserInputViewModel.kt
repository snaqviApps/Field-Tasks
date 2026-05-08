package create.develop.secondproj.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import create.develop.secondproj.data.UserServices
import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.toUserDetails
import create.develop.secondproj.domain.UserApi
import create.develop.secondproj.presentation.navigation.UiEvent
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInputViewModel(
    private val useServices: UserApi = UserServices()
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _userInputState =
        MutableStateFlow<UserInfoState>(UserInfoState.Success(POSTRequestBody()))
    val userInputState = _userInputState.asStateFlow()

    fun login() {
        val currentState = _userInputState.value

        // We only start login if we are currently in a Success (Input) state
        if (currentState !is UserInfoState.Success) return

        val requestBody = currentState.postRequestBody

        viewModelScope.launch {
            _userInputState.value = UserInfoState.Loading
            try {
                // 1. Perform Login
                val loginResponse = useServices.loginUser(requestBody)
                if (!loginResponse.isSuccessful) {
                    _userInputState.value = UserInfoState.Error("Login failed: ${loginResponse.message()}")
                    return@launch
                }

                // 2. Validate Token
                val token = loginResponse.body()?.accessToken
                if (token.isNullOrEmpty()) {
                    _userInputState.value = UserInfoState.Error("Login succeeded but no token was received")
                    return@launch
                }

                // 3. Fetch User Info
                val infoResponse = useServices.getUserInfo("Bearer $token")
                if (!infoResponse.isSuccessful) {
                    _userInputState.value = UserInfoState.Error("Failed to fetch profile: ${infoResponse.code()}")
                    return@launch
                }

                // 4. Validate & Objectify Body
                val userInfo = infoResponse.body()
                if (userInfo == null) {
                    _userInputState.value = UserInfoState.Error("User profile data is empty")
                    return@launch
                }

                // SUCCESS PATH
                val userDetails = userInfo.toUserDetails()
                Log.d("UserInputViewModel", "Fetch success: ${userDetails.email}")
                
                _uiEvent.emit(UiEvent.NavigateToDetail(userDetails))
                
                // Reset state for future use
                _userInputState.value = UserInfoState.Success(POSTRequestBody())

            } catch (e: Exception) {
                Log.e("UserInputViewModel", "Operation failed", e)
                _userInputState.value = UserInfoState.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

    fun onInputIdChanged(userName: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(username = userName))
            } else {
                UserInfoState.Success(POSTRequestBody(username = userName))
            }
        }
    }

    fun onInputNameChanged(password: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(password = password))
            } else {
                UserInfoState.Success(POSTRequestBody(password = password))
            }
        }
    }
}
