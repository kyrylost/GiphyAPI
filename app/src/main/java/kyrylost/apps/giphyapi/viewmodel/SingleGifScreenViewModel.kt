package kyrylost.apps.giphyapi.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kyrylost.apps.giphyapi.navigation.AppNavigator
import kyrylost.apps.giphyapi.navigation.Destination
import kyrylost.apps.giphyapi.viewstate.SingleGifScreenViewState
import javax.inject.Inject

@HiltViewModel
class SingleGifScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableStateFlow(SingleGifScreenViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val gifUrl = savedStateHandle.get<String>(Destination.SingleGifScreen.GIF_URL) ?: ""

        _viewState.update {
            it.copy(
                gifUrl = gifUrl
            )
        }
    }

//    fun onBackButtonClicked() {
//        appNavigator.tryNavigateBack()
//    }
}