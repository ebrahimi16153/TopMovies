package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieDetail
import com.github.ebrahimi16153.topmovies.navigation.ScreenRoute
import com.github.ebrahimi16153.topmovies.util.Error
import com.github.ebrahimi16153.topmovies.util.Loading
import com.github.ebrahimi16153.topmovies.viewModel.DetailViewModel
import kotlinx.coroutines.flow.update

@Composable
fun Detail(navHostController: NavHostController, movieId: Int, detailViewModel: DetailViewModel) {

    LaunchedEffect(key1 = movieId) {
        detailViewModel.getDetailMovie(movieId)
        detailViewModel.movieExists(movieId)

        navHostController.addOnDestinationChangedListener { _, destination, _ ->

            if (destination.route != ScreenRoute.Detail.name) {
                detailViewModel._detailMovie.update {
                    null
                }
            }

        }

    }

    val movieDetail = detailViewModel.detailMovie.collectAsState()
    val error = detailViewModel.apiError.collectAsState()
    val movieExists = detailViewModel.movieExists.collectAsState()
    val favoriteMovie = FavoriteMovie()

    if (movieDetail.value == null) {
        Loading()
    } else if (error.value.isNotEmpty()) {
        Error(value = error.value)
    } else {
        DetailContent(
            movieDetail = movieDetail.value!!,
            movieExists = movieExists.value,
            onBackClick = {
                navHostController.navigateUp()
            },
            onFavClick = {
                favoriteMovie.id = movieDetail.value!!.id!!
                favoriteMovie.country = movieDetail.value!!.country!!
                favoriteMovie.rate = movieDetail.value!!.rated!!
                favoriteMovie.year = movieDetail.value!!.year!!
                favoriteMovie.title = movieDetail.value!!.title!!
                favoriteMovie.poster = movieDetail.value!!.poster!!

                if (movieExists.value) {
                    detailViewModel.deleteFav(favoriteMovie)
                } else {
                    detailViewModel.addFav(favoriteMovie)
                }
            })
    }


}

@Composable
private fun DetailContent(
    movieDetail: ResponseOfMovieDetail?,
    movieExists: Boolean,
    onBackClick: () -> Unit,
    onFavClick: () -> Unit
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                contentAlignment = Alignment.BottomCenter

            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = movieDetail!!.poster!!,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.background.copy(alpha = 0.7f),
                                    MaterialTheme.colorScheme.background,
                                )
                            )
                        )
                )
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        //Back
                        OutlinedIconButton(onClick = { onBackClick() }) {
                            Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "")
                        }

                        //Fav
                        OutlinedIconButton(onClick = { onFavClick() }) {
                            Icon(
                                imageVector = Icons.Rounded.Favorite,
                                contentDescription = "",
                                tint = if (movieExists) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    //poster
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        model = movieDetail!!.poster!!,
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth
                    )

                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background,
                                )
                            )
                        ),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = movieDetail.title!!,
                            color = MaterialTheme.colorScheme.onBackground,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {

//                        ImdbRating
                                Icon(
                                    imageVector = Icons.Rounded.Star,
                                    contentDescription = "ImdbRating",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = movieDetail!!.imdbRating!!,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {

//                        country
                                Icon(
                                    imageVector = Icons.Rounded.LocationOn,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = movieDetail!!.country!!,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                //year
                                Icon(
                                    imageVector = Icons.Rounded.DateRange,
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Text(
                                    text = movieDetail!!.released!!,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontFamily = FontFamily.SansSerif
                                )
                            }


                        }
                        Spacer(modifier = Modifier.height(40.dp))

                    }

                }


            }


            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Info, contentDescription = "info")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Summery", style = MaterialTheme.typography.titleMedium)

            }
            Spacer(modifier = Modifier.height(8.dp))
            //summery
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieDetail!!.plot!!,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 16.dp)
            ) {
                Icon(imageVector = Icons.Rounded.Info, contentDescription = "info")
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Actors", style = MaterialTheme.typography.titleMedium)

            }
            Spacer(modifier = Modifier.height(8.dp))
            //actors
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = movieDetail!!.actors!!,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow {

                items(movieDetail!!.images!!) {
                    ActorsItem(image = it)
                }

            }
        }

    }
}


@Composable
fun ActorsItem(image: String?) {
    Box(
        modifier = Modifier
            .size(200.dp)
            .padding(5.dp),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(10.dp)),
            model = image,
            contentDescription = ""
        )

    }
}

