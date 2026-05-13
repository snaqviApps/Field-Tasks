package create.develop.loginservice.data.loggin.remote

import create.develop.loginservice.data.loggin.remote.logindetailsdata.Address
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

fun UserFetchedDetails.toUserDetails() : UserUIDetails {
    return UserUIDetails(
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
data class UserUIDetails (
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