package create.develop.secondproj.data

import create.develop.secondproj.domain.RetrofitBuilder.retrofit
import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.data.loggin.remote.UserProfile
import create.develop.secondproj.domain.UserApi
import retrofit2.Response

/**
 * Consolidated implementation of UserApi.
 * 
 * We create the 'api' instance once as a property for better performance,
 * as calling retrofit.create() repeatedly is expensive.
 */
class UserServices : UserApi {
    private val api = retrofit.create(UserApi::class.java)

    override suspend fun getUserInfo(token: String): Response<UserProfile> {
        return api.getUserInfo(token)
    }

    override suspend fun loginUser(postRequestBody: POSTRequestBody): Response<LoginResponse> {
        return api.loginUser(postRequestBody)
    }
}
