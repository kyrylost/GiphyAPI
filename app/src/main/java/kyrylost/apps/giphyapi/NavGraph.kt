package kyrylost.apps.giphyapi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kyrylost.apps.giphyapi.Screen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.GifsListScreen.route
    ) {
        composable(Screen.GifsListScreen.route) {
            GifsListScreen(
                navController,
                viewModel
            )
        }
        composable(
            Screen.SingleGifScreen.route,
            arguments = listOf(
                navArgument("gifUrl") {
                type = NavType.StringType })
        ) {
            val gifUrl = it.arguments?.getString("gifUrl") ?: ""

            SingleGifScreen(viewModel = viewModel, gifUrl = gifUrl)
        }
    }
}