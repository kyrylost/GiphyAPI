package kyrylost.apps.giphyapi.view

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kyrylost.apps.giphyapi.navigation.Destination
import kyrylost.apps.giphyapi.navigation.NavHost
import kyrylost.apps.giphyapi.navigation.NavigationIntent
import kyrylost.apps.giphyapi.navigation.composable
import kyrylost.apps.giphyapi.ui.theme.GiphyAPITheme
import kyrylost.apps.giphyapi.viewmodel.AppViewModel

@Composable
fun MainScreen(
    viewModel : AppViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = viewModel.navigationChannel,
        navHostController = navController
    )

    GiphyAPITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.GifsListScreen
            ) {
                composable(destination = Destination.GifsListScreen) {
                    GifsListScreen()
                }
                composable(destination = Destination.SingleGifScreen) {
                    SingleGifScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}