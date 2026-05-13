package create.develop.loginservice.data.loggin.remote.token

data class RefreshRequest(
    val refreshToken: String,
    val expiresInMins: Int = 30
)
