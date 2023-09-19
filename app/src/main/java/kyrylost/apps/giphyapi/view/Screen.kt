package kyrylost.apps.giphyapi.view

sealed class Screen(val route: String) {
    object GifsListScreen: Screen(route = "gifsListScreen")
    object SingleGifScreen: Screen(route = "singleGifScreen/{gifUrl}"
    ) {
        fun passGifUrl(
            gifUrl: String,
        ): String {
            return "singleGifScreen/$gifUrl"
        }
    }
}
