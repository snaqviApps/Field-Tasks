package create.develop.secondproj.presentation.navigation

import create.develop.secondproj.data.loggin.remote.UserUIDetails

sealed class UiEvent {
    data class NavigateToDetail(
        val userUIDetails: UserUIDetails
    ) : UiEvent()
}