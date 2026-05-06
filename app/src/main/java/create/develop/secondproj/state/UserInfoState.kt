package create.develop.secondproj.state

import create.develop.secondproj.data.loggin.local.POSTRequestBody


sealed interface UserInfoState {
    data object Loading : UserInfoState
    data class Success(val postRequestBody: POSTRequestBody) : UserInfoState
    data class Error(val eMessage: String) : UserInfoState
}
