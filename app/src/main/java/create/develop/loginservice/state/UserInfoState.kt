package create.develop.loginservice.state

import create.develop.loginservice.data.loggin.remote.POSTRequestBody


sealed interface UserInfoState {
    data object Loading : UserInfoState
    data class Success(val postRequestBody: POSTRequestBody) : UserInfoState
    data class Error(val eMessage: String) : UserInfoState
}
