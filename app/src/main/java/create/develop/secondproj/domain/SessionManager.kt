package create.develop.secondproj.domain

object SessionManager {
    @Volatile
    var accessToken: String? = null
    @Volatile
    var refreshToken: String? = null
}
