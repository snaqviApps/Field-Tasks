package create.develop.secondproj.data

import create.develop.secondproj.domain.RetrofitBuilder.retrofit
import create.develop.secondproj.data.loggin.local.LoginRequest
import create.develop.secondproj.data.loggin.remote.LoginResponse
import create.develop.secondproj.domain.UserGETApi
import create.develop.secondproj.domain.UserPOSTApi
import retrofit2.Response


class GetUserService : UserGETApi {
    override suspend fun getUserInfo(token: String): Response<LoginResponse> {
        return retrofit.create(UserGETApi::class.java).getUserInfo(token)
    }

}


class PostUserService : UserPOSTApi {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        return  retrofit.create(UserPOSTApi::class.java).loginUser(loginRequest)
    }


}
