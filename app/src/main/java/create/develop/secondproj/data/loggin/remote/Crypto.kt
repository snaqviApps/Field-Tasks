package create.develop.secondproj.data.loggin.remote

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val coin: String,
    val network: String,
    val wallet: String
)