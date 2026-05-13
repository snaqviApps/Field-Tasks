package create.develop.loginservice.presentation.navigation

import create.develop.loginservice.data.loggin.remote.UserUIDetails

sealed class UiEvent {
    data class NavigateToDetail(
        val userUIDetails: UserUIDetails
    ) : UiEvent()
}