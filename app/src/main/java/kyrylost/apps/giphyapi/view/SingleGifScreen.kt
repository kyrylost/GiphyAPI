package kyrylost.apps.giphyapi.view

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import kyrylost.apps.giphyapi.viewmodel.SingleGifScreenViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun SingleGifScreen(
    viewModel: SingleGifScreenViewModel = hiltViewModel()
) {

    Log.d("SingleGifScreen", "Launched")

    val context = LocalContext.current

    val viewState = viewModel.viewState.collectAsState().value

    val decodedUrl = remember{
        URLDecoder.decode(viewState.gifUrl, StandardCharsets.UTF_8.toString())
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