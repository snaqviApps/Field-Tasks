package create.develop.secondproj.presentation.navigation

import create.develop.secondproj.data.loggin.remote.UserDetails

sealed class UiEvent {
    data class NavigateToDetail(
        val userDetails: UserDetails
    ) : UiEvent()
}