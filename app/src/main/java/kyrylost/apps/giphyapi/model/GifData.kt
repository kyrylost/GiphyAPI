package kyrylost.apps.giphyapi.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GifData(
    val analytics: Analytics,
    @SerializedName("analytics_response_payload")
    val analyticsResponsePayload: String,
    @SerializedName("bitly_gif_url")
    val bitlyGifUrl: String,
    @SerializedName("bitly_url")
    val bitlyUrl: String,
    @SerializedName("content_url")
    val contentUrl: String,
    @SerializedName("embed_url")
    val embedUrl: String,
    val id: String,
    val images: Images,
    @SerializedName("import_datetime")
    val importDatetime: String,
    @SerializedName("is_sticker")
    val isSticker: Int,
    val rating: String,
    val slug: String,
    val source: String,
    @SerializedName("source_post_url")
    val sourcePostUrl: String,
    @SerializedName("source_tld")
    val sourceTld: String,
    val title: String,
    @SerializedName("trending_datetime")
    val trendingDatetime: String,
    val type: String,
    val url: String,
    val user: User,
    val username: String
) {
    @Keep
    data class Analytics(
        val onclick: Onclick,
        val onload: Onload,
        val onsent: Onsent
    ) {
        @Keep
        data class Onclick(
            val url: String
        )
        @Keep
        data class Onload(
            val url: String
        )
        @Keep
        data class Onsent(
            val url: String
        )
    }
    @Keep
    data class Images(
        @SerializedName("fixed_height")
        val fixedHeight: FixedHeight,
        @SerializedName("fixed_height_downsampled")
        val fixedHeightDownsampled: FixedHeightDownsampled,
        @SerializedName("fixed_height_small")
        val fixedHeightSmall: FixedHeightSmall,
        @SerializedName("fixed_width")
        val fixedWidth: FixedWidth,
        @SerializedName("fixed_width_downsampled")
        val fixedWidthDownsampled: FixedWidthDownsampled,
        @SerializedName("fixed_width_small")
        val fixedWidthSmall: FixedWidthSmall,
        val original: Original
    ) {
        @Keep
        data class FixedHeight(
            val height: String,
            val mp4: String,
            @SerializedName("mp4_size")
            val mp4Size: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class FixedHeightDownsampled(
            val height: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class FixedHeightSmall(
            val height: String,
            val mp4: String,
            @SerializedName("mp4_size")
            val mp4Size: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class FixedWidth(
            val height: String,
            val mp4: String,
            @SerializedName("mp4_size")
            val mp4Size: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class FixedWidthDownsampled(
            val height: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class FixedWidthSmall(
            val height: String,
            val mp4: String,
            @SerializedName("mp4_size")
            val mp4Size: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
        @Keep
        data class Original(
            val frames: String,
            val hash: String,
            val height: String,
            val mp4: String,
            @SerializedName("mp4_size")
            val mp4Size: String,
            val size: String,
            val url: String,
            val webp: String,
            @SerializedName("webp_size")
            val webpSize: String,
            val width: String
        )
    }
    @Keep
    data class User(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("banner_image")
        val bannerImage: String,
        @SerializedName("banner_url")
        val bannerUrl: String,
        val description: String,
        @SerializedName("display_name")
        val displayName: String,
        @SerializedName("instagram_url")
        val instagramUrl: String,
        @SerializedName("is_verified")
        val isVerified: Boolean,
        @SerializedName("profile_url")
        val profileUrl: String,
        val username: String,
        @SerializedName("website_url")
        val websiteUrl: String
    )
}