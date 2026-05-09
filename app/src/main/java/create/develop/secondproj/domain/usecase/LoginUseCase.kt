package create.develop.secondproj.domain.usecase

import create.develop.secondproj.data.loggin.remote.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.UserFetchedDetails
import create.develop.secondproj.domain.SessionManager
import create.develop.secondproj.domain.UserApi

class LoginUseCase(
    private val userApi: UserApi
) {
    /**
     * Executes the login business logic:
     * 1. Authenticates the user via the API.
     * 2. Enforces the business rule: Update the global SessionManager with new tokens.
     * 3. Fetches the full user profile using the established session.
     *
     * @param request The login credentials.
     * @return The detailed user information.
     * @throws Exception if any step of the process fails.
     */
    suspend fun execute(request: POSTRequestBody): UserFetchedDetails {
        // 1. Perform Login
        val loginResponse = userApi.loginUser(request)
        if (!loginResponse.isSuccessful) {
            throw Exception("Login failed with code: ${loginResponse.code()}")
        }

        val loginBody = loginResponse.body()
            ?: throw Exception("Login successful but response body was empty")

        // 2. Business Logic: Update the Session state in the Domain layer
        // This is a key business rule: successful authentication must update the session.
        SessionManager.accessToken = loginBody.accessToken
        SessionManager.refreshToken = loginBody.refreshToken

        // 3. Fetch User Profile
        // The Retrofit Interceptor (in the data layer) will automatically pick up the
        // new token from SessionManager to authorize this request.
        val infoResponse = userApi.getUserInfo()
        if (!infoResponse.isSuccessful) {
            throw Exception("Profile fetch failed with code: ${infoResponse.code()}")
        }

        return infoResponse.body()
            ?: throw Exception("User profile data is missing")
    }
}
