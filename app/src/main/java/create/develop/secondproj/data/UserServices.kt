package create.develop.secondproj.data

import create.develop.secondproj.data.loggin.remote.RetrofitBuilder.retrofit
import create.develop.secondproj.data.loggin.remote.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.data.loggin.remote.UserFetchedDetails
import create.develop.secondproj.data.loggin.remote.token.RefreshRequest
import create.develop.secondproj.data.loggin.remote.token.TokenResponse
import create.develop.secondproj.domain.UserApi
import retrofit2.Call
import retrofit2.Response

class UserServices : UserApi {
    private val api = retrofit.create(UserApi::class.java)

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
