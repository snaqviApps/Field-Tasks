package create.develop.secondproj.domain

import create.develop.secondproj.data.logging.UserInput

interface GetUserInput {
    suspend fun getUserInfo(
        id: String,
        name: String,
    ): UserInput
}
