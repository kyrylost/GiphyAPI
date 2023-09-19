package kyrylost.apps.giphyapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GifDataList(
    val `data`: List<GifData>,
    val meta: Meta,
    val pagination: Pagination
) {
    @Keep
    data class Meta(
        val msg: String,
        @SerializedName("response_id")
        val responseId: String,
        val status: Int
    )
    @Keep
    data class Pagination(
        val count: Int,
        val offset: Int,
        @SerializedName("total_count")
        val totalCount: Int
    )
}