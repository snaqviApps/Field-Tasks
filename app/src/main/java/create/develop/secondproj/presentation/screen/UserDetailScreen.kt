package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserDetailScreen(
    id: String,
    name: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "User Details", style = MaterialTheme.typography.headlineMedium)
        Text(text = "ID: $id", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Name: $name", style = MaterialTheme.typography.bodyLarge)
    }
}
