package kyrylost.apps.giphyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kyrylost.apps.giphyapi.api.ApiRepository
import kyrylost.apps.giphyapi.model.GifData
import kyrylost.apps.giphyapi.navigation.AppNavigator
import kyrylost.apps.giphyapi.navigation.Destination
import kyrylost.apps.giphyapi.viewstate.GifsListScreenViewState
import javax.inject.Inject

@HiltViewModel
class GifsListScreenViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _viewState = MutableStateFlow(GifsListScreenViewState())
    val viewState = _viewState.asStateFlow()

    fun getGifs(): Flow<PagingData<GifData>> = apiRepository.getGifs().cachedIn(viewModelScope)

    fun saveListScrollPosition(index: Int, offset: Int = 0) {
        _viewState.update {
            it.copy(
                listIndex = index,
                listOffset = offset
            )
        }
    }

    fun saveGridScrollPosition(index: Int, offset: Int = 0) {
        _viewState.update {
            it.copy(
                gridIndex = index,
                gridOffset = offset
            )
        }
    }

    fun saveGridType(columnSelected: Boolean) {
        _viewState.update {
            it.copy(
                isColumnSelected = columnSelected
            )
        }
    }

    fun onGifClicked(gifUrl: String) {
        appNavigator.tryNavigateTo(
            Destination.SingleGifScreen(gifUrl = gifUrl)
        )
    }
}