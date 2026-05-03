package create.develop.secondproj.domain

import create.develop.secondproj.data.loggin.local.LoginRequest
import create.develop.secondproj.data.loggin.remote.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * fetch('https://dummyjson.com/auth/login', {
 *   method: 'POST',
 *   headers: { 'Content-Type': 'application/json' },
 *   body: JSON.stringify({
 *     username: 'emilys',
 *     password: 'emilyspass',
 *   })
 * })
 * .then(res => res.json())    // WHEN the response arrives, convert it to JSON
 * .then(data => {             // WHEN the conversion is done, do something with the data
 *   console.log(data);
 * })
 *
 */

interface UserApi {
    /**
     * Equivalent to the JS fetch('https://dummyjson.com/auth/me')
     * method: 'GET',
     * headers: { 'Authorization': 'Bearer ...' }
     */
    @GET("auth/me")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): Response<LoginResponse>

    @POST("auth/login")
    suspend fun loginUser(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>
}
