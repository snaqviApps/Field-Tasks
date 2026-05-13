package create.develop.loginservice.domain.usecase

object SessionManager {
    @Volatile
    var accessToken: String? = null
    @Volatile
    var refreshToken: String? = null
}