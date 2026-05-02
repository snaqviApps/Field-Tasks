package create.develop.secondproj.domain

import create.develop.secondproj.data.logging.local.LoginRequest
import create.develop.secondproj.data.logging.remote.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

// BASE URL: https://dummyjson.com/

/**
 * Equivalent to the JS fetch('https://dummyjson.com/auth/me')
 * method: 'GET',
 * headers: { 'Authorization': 'Bearer ...' }
 */
interface UserGETApi {
    @GET("auth/me")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<LoginResponse>                              // reusing LoginResponse class for now
}

interface UserPOSTApi {
    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
