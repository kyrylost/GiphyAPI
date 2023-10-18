package kyrylost.apps.giphyapi.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kyrylost.apps.giphyapi.navigation.Destination

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Destination,
//    viewModel: AppViewModel,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder
    )
}
fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )
}
//    NavHost(
//        modifier = Modifier.fillMaxSize(),
//        navController = navController,
//        startDestination = Screen.GifsListScreen.route
//    ) {
//        composable(Screen.GifsListScreen.route) {
//            GifsListScreen(
//                navController,
//                viewModel
//            )
//        }
//        composable(
//            Screen.SingleGifScreen.route,
//            arguments = listOf(
//                navArgument("gifUrl") {
//                type = NavType.StringType })
//        ) {
//            val gifUrl = it.arguments?.getString("gifUrl") ?: ""
//
//            SingleGifScreen(gifUrl = gifUrl)
//        }
//    }