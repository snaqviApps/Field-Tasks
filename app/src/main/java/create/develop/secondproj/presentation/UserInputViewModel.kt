package create.develop.secondproj.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import create.develop.secondproj.data.UserServices
import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.UserProfile
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
            // Step 1: Set state to Loading
            _userInputState.value = UserInfoState.Loading
            try {
                // Step 2: Perform Login
                val loginResponse = useServices.loginUser(requestBody)

                if (loginResponse.isSuccessful) {
                    val token = loginResponse.body()?.accessToken

                    if (!token.isNullOrEmpty()) {
                        // Step 3: Fetch User Info using the token
                        val infoResponse = useServices.getUserInfo("Bearer $token")

                        if (infoResponse.isSuccessful) {
                            val userDetailsInfo: UserProfile? = infoResponse.body()

                            if (userDetailsInfo != null) {
                                val userDetails = userDetailsInfo.toUserDetails()

                                // Success -> Navigate
                                _uiEvent.emit(UiEvent.NavigateToDetail(userDetails = userDetails))

                                // Reset state back to Success (Input) so UI is ready if we navigate back
                                _userInputState.value = UserInfoState.Success(POSTRequestBody())
                            } else {
                                _userInputState.value = UserInfoState.Error("User profile is null")
                            }
                        } else {
                            _userInputState.value =
                                UserInfoState.Error("Failed to fetch user profile: ${infoResponse.code()}")
                        }
                    } else {
                        _userInputState.value =
                            UserInfoState.Error("Login succeeded but no token was received")
                    }
                } else {
                    _userInputState.value =
                        UserInfoState.Error("Login failed: ${loginResponse.message()}")
                }
            } catch (e: Exception) {
                _userInputState.value =
                    UserInfoState.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

    fun onInputIdChanged(userName: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(username = userName))
            } else {
                // If in Error/Loading, typing resets to Success with the new input
                UserInfoState.Success(POSTRequestBody(username = userName))
            }
        }
    }

    fun onInputNameChanged(password: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(password = password))
            } else {
                // If in Error/Loading, typing resets to Success with the new input
                UserInfoState.Success(POSTRequestBody(password = password))
            }
        }
    }
}
