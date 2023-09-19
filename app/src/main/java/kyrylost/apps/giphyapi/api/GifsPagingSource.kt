package kyrylost.apps.giphyapi.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kyrylost.apps.giphyapi.model.GifData

class GifsPagingSource(private val apiService: ApiService)
    : PagingSource<Int, GifData>() {
    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        return try {
            val page = params.key ?: 1
            val response = apiService.getGifs(offset = page * 20) // 20 is amount of items per response

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.data.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}