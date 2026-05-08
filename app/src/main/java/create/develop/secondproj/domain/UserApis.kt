package create.develop.secondproj.domain

import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.data.loggin.remote.UserProfile
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {

    @GET("auth/me")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
//    ): Response<LoginResponse>
    ): Response<UserProfile>

    /**
     * makes a 'POST' Service-call to the login server,
     * where by [ ](like GET), POST is an HTTP Method (also called verb) ]
     */
    @POST("auth/login")
    suspend fun loginUser(
        @Body postRequestBody: POSTRequestBody
    ): Response<LoginResponse>
}
