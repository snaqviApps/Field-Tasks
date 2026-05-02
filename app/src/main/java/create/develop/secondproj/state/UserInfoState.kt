package create.develop.secondproj.state

import create.develop.secondproj.data.logging.local.LoginRequest


sealed interface UserInfoState {
    data object Loading : UserInfoState
    data class Success(val loginRequest: LoginRequest) : UserInfoState
    data class Error(val message: String) : UserInfoState
}
