package kyrylost.apps.giphyapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kyrylost.apps.giphyapi.ui.theme.GiphyAPITheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyAPITheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    viewModel
                )
            }
        }
    }
}