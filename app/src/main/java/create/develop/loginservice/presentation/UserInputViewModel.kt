package create.develop.loginservice.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import create.develop.loginservice.data.loggin.UserServices
import create.develop.loginservice.data.loggin.remote.POSTRequestBody
import create.develop.loginservice.data.loggin.remote.toUserDetails
import create.develop.loginservice.domain.usecase.SessionManager
import create.develop.loginservice.domain.usecase.LoginUseCase
import create.develop.loginservice.presentation.navigation.UiEvent
import create.develop.loginservice.state.UserInfoState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserInputViewModel(
    // We create an instance of services to share between the UseCase and the ViewModel test functions
    private val userServices: UserServices = UserServices(),
    private val loginUseCase: LoginUseCase = LoginUseCase(userServices)
) : ViewModel() {

    private val _uiEvent = MutableSharedFlow<UiEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val _userInputState =
        MutableStateFlow<UserInfoState>(UserInfoState.Success(POSTRequestBody()))
    val userInputState = _userInputState.asStateFlow()

    fun login() {
        val currentState = _userInputState.value as? UserInfoState.Success ?: return
        val requestBody = currentState.postRequestBody

        viewModelScope.launch {
            _userInputState.value = UserInfoState.Loading
            try {
                // The UseCase now handles:
                // 1. API Login
                // 2. Saving tokens to SessionManager (Business Logic)
                // 3. Fetching User Profile
                val userFetchedDetails = loginUseCase.execute(requestBody)

                // Success path: Transform the data model to the UI model and navigate
                val userUIDetails = userFetchedDetails.toUserDetails()
                Log.d("UserInputViewModel", "Login flow completed for: ${userUIDetails.email}")

                _uiEvent.emit(UiEvent.NavigateToDetail(userUIDetails))
                
                // Reset state for future use
                _userInputState.value = UserInfoState.Success(POSTRequestBody())

            } catch (e: Exception) {
                Log.e("UserInputViewModel", "Login flow failed", e)
                _userInputState.value = UserInfoState.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }

    fun testRefresh() {
        // Clear token to force a 401 and trigger the silent refresh in Authenticator
        SessionManager.accessToken = null
        Log.d("TestRefresh", "Token cleared. Making request to trigger silent refresh...")

        viewModelScope.launch {
            try {
                // This call will hit a 401, causing the Authenticator to run
                val response = userServices.getUserInfo()

                if (response.isSuccessful) {
                    Log.d("TestRefresh", "SUCCESS! Authenticator refreshed token and retried successfully.")
                    Log.d("TestRefresh", "New Token: ${SessionManager.accessToken}")
                } else {
                    Log.e("TestRefresh", "Failed with code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("TestRefresh", "Request failed", e)
            }
        }
    }

    fun onInputNameChanged(userName: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(username = userName))
            } else {
                UserInfoState.Success(POSTRequestBody(username = userName))
            }
        }
    }

    fun onInputPasswordChanged(password: String) {
        _userInputState.update { currentState ->
            if (currentState is UserInfoState.Success) {
                currentState.copy(postRequestBody = currentState.postRequestBody.copy(password = password))
            } else {
                UserInfoState.Success(POSTRequestBody(password = password))
            }
        }
    }
}
