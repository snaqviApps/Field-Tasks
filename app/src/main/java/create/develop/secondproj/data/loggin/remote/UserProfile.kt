package create.develop.secondproj.data.loggin.remote

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val address: Address,
    val age: Int,
    val bank: Bank = Bank(
        cardExpire = "",
        cardNumber = "",
        cardType = "",
        currency = "",
        iban = ""
    ),
    val birthDate: String,
    val bloodGroup: String,
    val company: Company,
    val crypto: Crypto,
    val ein: String,
    val email: String,
    val eyeColor: String,
    val firstName: String,
    val gender: String,
    val hair: Hair,
    val height: Double,
    val id: Int,
    val image: String,
    val ip: String,
    val lastName: String,
    val macAddress: String,
    val maidenName: String,
    val password: String,
    val phone: String,
    val role: String,
    val ssn: String,
    val university: String,
    val userAgent: String,
    val username: String,
    val weight: Double
)