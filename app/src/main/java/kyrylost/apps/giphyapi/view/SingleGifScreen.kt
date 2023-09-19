package kyrylost.apps.giphyapi.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun SingleGifScreen(gifUrl: String) {
    val context = LocalContext.current
    val decodedUrl = remember{
        URLDecoder.decode(gifUrl, StandardCharsets.UTF_8.toString())
    }
    val imageLoader = remember {
        ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()
    }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(decodedUrl)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .fillMaxSize(),
        imageLoader = imageLoader
    )
}