package kyrylost.apps.giphyapi

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import kyrylost.apps.giphyapi.model.GifData

@Composable
fun GifsListScreen(navController : NavController,
                   viewModel : AppViewModel) {

    val results = viewModel.getGifs().collectAsLazyPagingItems()

    var columnSelected by remember {
        mutableStateOf(viewModel.isColumnSelected)
    }
    var itemClicked by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    val gridState = rememberLazyGridState()
    val listState = rememberLazyListState()

    val coroutineScope = rememberCoroutineScope()

    Column {

        @Composable
        fun onChangeToGrid() {
            LazyVerticalGrid (
                modifier = Modifier.background(colorResource(R.color.black)),
                columns = GridCells.Fixed(2),
                state = gridState
            ) {

                if(gridState.firstVisibleItemIndex < viewModel.gridIndex) {
                    coroutineScope.launch {
                        gridState.animateScrollToItem(viewModel.gridIndex, viewModel.gridOffset)
                    }
                }
                else if (!itemClicked){
                    viewModel.saveGridScrollPosition(0)
                }

                item {
                    Row (modifier = Modifier.padding(bottom = 40.dp, top = 20.dp)) {
                        Image(
                            painterResource(id = R.drawable.grid_icon),
                            contentDescription = "Grid",
                            modifier = Modifier
                                .clickable {
                                    viewModel.saveGridType(false)
                                    columnSelected = false
                                }
                                .height(32.dp)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                        Image(
                            painterResource(id = R.drawable.column_icon),
                            contentDescription = "Column",
                            modifier = Modifier
                                .clickable {
                                    viewModel.saveGridType(true)
                                    columnSelected = true
                                }
                                .height(32.dp)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                }
                item {  }

                items(results.itemCount) { index ->
                    val gifData = results[index]
                    GifListItem(gifData = gifData!!, navController)
                }

                when (val state = results.loadState.refresh) { //FIRST LOAD
                    is LoadState.Error -> {
                        if(state.error.localizedMessage != null) {
                            Toast.makeText(context,
                                state.error.localizedMessage!!.toString(),
                                Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is LoadState.Loading -> { // Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Refresh Loading"
                                )

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }

                when (val state = results.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        if(state.error.localizedMessage != null) {
                            Toast.makeText(context,
                                state.error.localizedMessage!!.toString(),
                                Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> {}
                }
            }
        }


        @Composable
        fun onChangeToColumn() {
            LazyColumn(
                modifier = Modifier.background(colorResource(R.color.black)),
                state = listState
            ) {

                if(listState.firstVisibleItemIndex < viewModel.listIndex) {
                    coroutineScope.launch {
                        listState.animateScrollToItem(viewModel.listIndex, viewModel.listOffset)
                    }
                }
                else if (!itemClicked){
                    viewModel.saveListScrollPosition(0)
                }

                item {
                    Row (modifier = Modifier.padding(bottom = 40.dp, top = 20.dp)) {
                        Image(
                            painterResource(id = R.drawable.grid_icon),
                            contentDescription = "Grid",
                            modifier = Modifier
                                .clickable {
                                    viewModel.saveGridType(false)
                                    columnSelected = false
                                }
                                .height(32.dp)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                        Image(
                            painterResource(id = R.drawable.column_icon),
                            contentDescription = "Column",
                            modifier = Modifier
                                .clickable {
                                    viewModel.saveGridType(true)
                                    columnSelected = true
                                }
                                .height(32.dp)
                                .padding(start = 8.dp, end = 8.dp)
                        )
                    }
                }

                items(count = results.itemCount) { index ->
                    val item = results[index]
                    GifListItem(gifData = item!!, navController)
                }

                when (val state = results.loadState.refresh) { //FIRST LOAD
                    is LoadState.Error -> {
                        if(state.error.localizedMessage != null) {
                            Toast.makeText(context,
                                state.error.localizedMessage!!.toString(),
                                Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is LoadState.Loading -> { // Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier = Modifier
                                        .padding(8.dp),
                                    text = "Refresh Loading"
                                )

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> { }
                }

                when (val state = results.loadState.append) { // Pagination
                    is LoadState.Error -> {
                        if(state.error.localizedMessage != null) {
                            Toast.makeText(context,
                                state.error.localizedMessage!!.toString(),
                                Toast.LENGTH_LONG)
                                .show()
                        }
                        else {
                            Toast.makeText(context,
                                "Error",
                                Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                    is LoadState.Loading -> { // Pagination Loading UI
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator(color = Color.Black)
                            }
                        }
                    }
                    else -> { }
                }
            }
        }


        Column (modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(colorResource(R.color.black))
        ){
            Text(
                text = "Popular now",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(top = 40.dp, bottom = 20.dp, start = 8.dp)
            )
        }

        if (columnSelected) onChangeToColumn()
        else onChangeToGrid()

    }
}

@Composable
fun GifListItem(gifData: GifData, navController: NavController) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            add(ImageDecoderDecoder.Factory())
        }
        .build()

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(gifData.images.original.url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape),
        imageLoader = imageLoader
    )
}