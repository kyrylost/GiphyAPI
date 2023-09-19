package kyrylost.apps.giphyapi.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService){
    fun getGifs() = Pager(
        config = PagingConfig(
            pageSize = 20,
        ),
        pagingSourceFactory = {
            GifsPagingSource(apiService)
        }
    ).flow
}