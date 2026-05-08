package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.state.UserInfoState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: UserInfoState.Success,
    onInputChangedUserName: (String) -> Unit,
    onInputChangedPassword: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        shape = CardDefaults.elevatedShape,
        elevation = CardDefaults.elevatedCardElevation(12.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.postRequestBody.username,
                onValueChange = { userName ->
                    onInputChangedUserName(userName)
                },
                label = { Text("Username") }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.postRequestBody.password,
                onValueChange = { password ->
                    onInputChangedPassword(password)
                },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Log in")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    LoginScreen(
        state = UserInfoState.Success(postRequestBody = POSTRequestBody().copy(username = "username", password = "password")),
        onInputChangedUserName = {},
        onInputChangedPassword = {},
        onSubmit = {}
    )

}
