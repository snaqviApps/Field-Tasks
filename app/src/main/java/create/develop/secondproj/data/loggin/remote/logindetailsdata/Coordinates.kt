package create.develop.secondproj.data.loggin.remote.logindetailsdata

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val lat: Double,
    val lng: Double
)