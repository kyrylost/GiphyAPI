package kyrylost.apps.giphyapi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kyrylost.apps.giphyapi.api.ApiRepository
import kyrylost.apps.giphyapi.model.GifData
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    fun getGifs(): Flow<PagingData<GifData>> = apiRepository.getGifs().cachedIn(viewModelScope)

    var listIndex = 0
    var listOffset = 0

    var gridIndex = 0
    var gridOffset = 0

    var isColumnSelected = false

    fun saveListScrollPosition(index: Int, offset: Int = 0) {
        listIndex = index
        listOffset = offset
    }

    fun saveGridScrollPosition(index: Int, offset: Int = 0) {
        gridIndex = index
        gridOffset = offset
    }

    fun saveGridType(columnSelected: Boolean) {
        isColumnSelected = columnSelected
    }

}