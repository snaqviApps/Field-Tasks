package create.develop.loginservice.domain

import create.develop.loginservice.data.loggin.remote.token.RefreshRequest
import create.develop.loginservice.data.loggin.remote.LoginResponse
import create.develop.loginservice.data.loggin.remote.POSTRequestBody
import create.develop.loginservice.data.loggin.remote.UserFetchedDetails
import create.develop.loginservice.data.loggin.remote.token.TokenResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @GET("auth/me")
    suspend fun getUserInfo(
        @Header("Authorization") token: String? = null
    ): Response<UserFetchedDetails>

    @POST("auth/login")
    suspend fun loginUser(
        @Body postRequestBody: POSTRequestBody
    ): Response<LoginResponse>

    // Use TokenResponse instead of LoginResponse for refresh
    @POST("auth/refresh")
    fun refreshToken(
        @Body refreshRequest: RefreshRequest
    ): Call<TokenResponse>
}
