package create.develop.secondproj

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import create.develop.secondproj.presentation.navigation.NavInitComponent
import create.develop.secondproj.presentation.screen.UserInputScreen
import create.develop.secondproj.ui.theme.SecondProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SecondProjTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)
                    NavInitComponent(modifier)
                }
            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun UserInputScreenPreview() {
    SecondProjTheme {
        UserInputScreen(onNavigateToDetail = {_, _, _, _, _, _, _, _, _ -> })
    }
}
