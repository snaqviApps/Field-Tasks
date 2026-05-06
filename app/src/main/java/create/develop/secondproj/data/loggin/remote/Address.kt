package create.develop.secondproj.data.loggin.remote

import kotlinx.serialization.Serializable

@Serializable
data class Address(
    val address: String,
    val city: String,
    val coordinates: Coordinates,
    val country: String,
    val postalCode: String,
    val state: String,
    val stateCode: String
)