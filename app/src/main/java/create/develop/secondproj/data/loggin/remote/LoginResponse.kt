package create.develop.secondproj.data.loggin.remote

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

fun LoginResponse.toUserProfile() : UserProfile {
    return UserProfile(
        email = email,
        firstName = firstName,
        gender = gender,
        image = image,
        lastName = lastName,
    )
}

data class UserProfile (
    val email: String,
    val firstName: String,
    val gender: String,
    val image: String,
    val lastName: String,
)