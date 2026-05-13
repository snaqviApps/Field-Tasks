package create.develop.loginservice.presentation.navigation

import androidx.navigation3.runtime.NavKey
import create.develop.loginservice.data.loggin.remote.UserUIDetails
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {

    @Serializable
    data object Input : Screen

    @Serializable
    data class Detail(val userDetail: UserUIDetails) : Screen
}