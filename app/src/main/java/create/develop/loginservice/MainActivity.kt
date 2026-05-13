package create.develop.loginservice

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
import create.develop.loginservice.presentation.navigation.NavInitComponent
import create.develop.loginservice.presentation.screen.UserInputScreen
import create.develop.loginservice.ui.theme.LoginServiceProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginServiceProjTheme {
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
    LoginServiceProjTheme {
        UserInputScreen(onNavigateToDetail = { _-> }
        )
    }
}
