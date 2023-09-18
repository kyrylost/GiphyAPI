package kyrylost.apps.giphyapi.api

import kyrylost.apps.giphyapi.model.GifDataList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("trending?api_key=6ZeVtDjy9aZWcuV7t2Cr1nTNC7w2Nzj7&limit=25&offset=0&rating=g&bundle=messaging_non_clips")
    suspend fun getGifs(@Query("page") page: Int): GifDataList
}