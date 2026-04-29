package create.develop.secondproj.data

import create.develop.secondproj.data.logging.UserInput
import create.develop.secondproj.domain.GetUserInput

class GetUserInputService : GetUserInput {

    override suspend fun getUserInfo(
        id: String,
        name: String
    ) : UserInput {
        print("here in @GetUserInputService: $id $name")
        return UserInput(id, name)
    }
}