package create.develop.secondproj.presentation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen : NavKey {

    @Serializable
    data object Input : Screen

    @Serializable
    data class Detail(val id: String, val name: String) : Screen
}
