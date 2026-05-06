package create.develop.secondproj.data.loggin.remote

import kotlinx.serialization.Serializable

data class LoginResponse(
    val accessToken: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val image: String,
    val lastName: String,
    val refreshToken: String,
    val username: String
)

fun UserProfile.toUserDetails() : UserDetails {
    return UserDetails(
        email = email,
        firstName = firstName,
        gender = gender,
        image = image,
        lastName = lastName,
        birthDate = birthDate,
        bloodGroup = bloodGroup,
        business = company.address,
        address = address
    )
}

@Serializable
data class UserDetails (
    val email: String,
    val firstName: String,
    val gender: String,
    val image: String,
    val lastName: String,
    val birthDate: String,
    val bloodGroup: String,
    val address: Address?,
    val business: Address?,          // needs to come from Company class
)