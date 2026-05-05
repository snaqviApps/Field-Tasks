package create.develop.secondproj.presentation.navigation

sealed class UiEvent {
    data class NavigateToDetail(val username: String, val password: String) : UiEvent()
}