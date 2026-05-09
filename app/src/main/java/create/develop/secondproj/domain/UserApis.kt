package create.develop.secondproj.domain

import create.develop.secondproj.data.loggin.remote.token.RefreshRequest
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.data.loggin.remote.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.UserFetchedDetails
import create.develop.secondproj.data.loggin.remote.token.TokenResponse
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
