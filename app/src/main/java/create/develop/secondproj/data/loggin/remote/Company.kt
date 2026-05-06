package create.develop.secondproj.data.loggin.remote

import kotlinx.serialization.Serializable

@Serializable
data class Company(
    val address: Address,
    val department: String,
    val name: String,
    val title: String
)