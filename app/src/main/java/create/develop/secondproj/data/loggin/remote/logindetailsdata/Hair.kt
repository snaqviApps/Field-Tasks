package create.develop.secondproj.data.loggin.remote.logindetailsdata

import kotlinx.serialization.Serializable

@Serializable
data class Hair(
    val color: String,
    val type: String
)