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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import create.develop.secondproj.data.loggin.local.POSTRequestBody
import create.develop.secondproj.state.UserInfoState
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    state: UserInfoState.Success,
    onInputChangedUserName: (String) -> Unit,
    onInputChangedPassword: (String) -> Unit,
    onSubmit: () -> Unit
) {
    // State to track if the last character should be temporarily visible
    var isLastCharVisible by remember { mutableStateOf(false) }

    // When password changes, show the last character for 1 second (the "echo")
    LaunchedEffect(state.postRequestBody.password) {
        if (state.postRequestBody.password.isNotEmpty()) {
            isLastCharVisible = true
            delay(1000)
            isLastCharVisible = false
        }
    }

    // Custom VisualTransformation for the "echo" effect
    val passwordTransformation = remember(isLastCharVisible) {
        VisualTransformation { text ->
            val transformedText = if (isLastCharVisible && text.isNotEmpty()) {
                val lastCharIndex = text.length - 1
                val maskedPart = "•".repeat(lastCharIndex)
                val lastChar = text[lastCharIndex]
                AnnotatedString(maskedPart + lastChar)
            } else {
                AnnotatedString("•".repeat(text.length))
            }
            TransformedText(transformedText, OffsetMapping.Identity)
        }
    }

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
                // Applying the custom echo transformation
                visualTransformation = passwordTransformation,
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
