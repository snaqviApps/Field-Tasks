package create.develop.secondproj.data

import create.develop.secondproj.data.logging.local.LoginRequest
import create.develop.secondproj.data.logging.remote.LoginResponse
import create.develop.secondproj.domain.UserGETApi
import create.develop.secondproj.domain.UserPOSTApi
import retrofit2.Response

class GetUserService : UserGETApi {
    override suspend fun getUserInfo(token: String): Response<LoginResponse> {
        TODO("Not yet implemented")
    }

}


class PostUserService : UserPOSTApi {
    override suspend fun loginUser(loginRequest: LoginRequest): Response<LoginResponse> {
        TODO("Not yet implemented")
    }


}