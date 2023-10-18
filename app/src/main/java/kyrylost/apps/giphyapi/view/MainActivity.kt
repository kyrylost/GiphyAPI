package kyrylost.apps.giphyapi.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kyrylost.apps.giphyapi.viewmodel.AppViewModel
import kyrylost.apps.giphyapi.ui.theme.GiphyAPITheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GiphyAPITheme {
                MainScreen()
            }
        }
    }
}