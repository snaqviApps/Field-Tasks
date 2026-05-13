package create.develop.loginservice.data.loggin.remote.logindetailsdata

import kotlinx.serialization.Serializable

@Serializable
data class Crypto(
    val coin: String,
    val network: String,
    val wallet: String
)