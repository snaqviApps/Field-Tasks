package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import create.develop.secondproj.state.UserInfoState

@Composable
fun UserInputProvider(
    modifier: Modifier = Modifier,
    state: UserInfoState,
    onInputChangedID: (String) -> Unit,
    onInputChangedName: (String) -> Unit,
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
                value = state.userInput.id,
                onValueChange = { id ->
                    onInputChangedID(id)
                }
            )
            Spacer(Modifier.height(8.dp))
            TextField(
                value = state.userInput.name,
                onValueChange = { name ->
                    onInputChangedName(name)
                }
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onSubmit,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Submit")
            }
        }
    }
}
