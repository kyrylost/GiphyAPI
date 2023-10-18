package kyrylost.apps.giphyapi.navigation

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object GifsListScreen : NoArgumentsDestination("gifsListScreen")

    object SingleGifScreen : Destination("singleGifScreen", "gifUrl") {
        const val GIF_URL = "gifUrl"

        operator fun invoke(gifUrl: String): String = route.appendParams(
            GIF_URL to gifUrl,
        )
    }

    internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
        val builder = StringBuilder(this)

        params.forEach {
            it.second?.toString()?.let { arg ->
                builder.append("/$arg")
            }
        }

        return builder.toString()
    }
//    object GifsListScreen: Screen(route = "gifsListScreen")
//    object SingleGifScreen: Screen(route = "singleGifScreen/{gifUrl}"
//    ) {
//        fun passGifUrl(
//            gifUrl: String,
//        ): String {
//            return "singleGifScreen/$gifUrl"
//        }
//    }
}
