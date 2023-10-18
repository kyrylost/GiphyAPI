package kyrylost.apps.giphyapi.viewstate

data class GifsListScreenViewState(
    var listIndex: Int = 0,
    var listOffset: Int = 0,

    var gridIndex: Int = 0,
    var gridOffset: Int = 0,

    var isColumnSelected: Boolean = false
)