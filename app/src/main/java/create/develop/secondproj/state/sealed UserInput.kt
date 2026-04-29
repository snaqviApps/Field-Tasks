package create.develop.secondproj.state

import create.develop.secondproj.data.logging.UserInput

sealed interface UserInfoState {
    data object Loading : UserInfoState
    data class Success(val userInput: UserInput) : UserInfoState
    data class Error(val message: String) : UserInfoState
}
