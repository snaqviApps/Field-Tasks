package create.develop.loginservice.data.loggin

import create.develop.loginservice.data.loggin.remote.LoginResponse
import create.develop.loginservice.data.loggin.remote.POSTRequestBody
import create.develop.loginservice.data.loggin.remote.RetrofitBuilder
import create.develop.loginservice.data.loggin.remote.UserFetchedDetails
import create.develop.loginservice.data.loggin.remote.token.RefreshRequest
import create.develop.loginservice.data.loggin.remote.token.TokenResponse
import create.develop.loginservice.domain.UserApi
import retrofit2.Call
import retrofit2.Response

class UserServices : UserApi {
    private val api = RetrofitBuilder.retrofit.create(UserApi::class.java)

    override suspend fun getUserInfo(token: String?): Response<UserFetchedDetails> {
        return api.getUserInfo(token)
    }

    override suspend fun loginUser(postRequestBody: POSTRequestBody): Response<LoginResponse> {
        return api.loginUser(postRequestBody)
    }

    override fun refreshToken(refreshRequest: RefreshRequest): Call<TokenResponse> {
      return api.refreshToken(refreshRequest)
    }
}