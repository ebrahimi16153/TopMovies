package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.viewModel.FavViewModel

@Composable
fun Favorite(navHostController: NavHostController, favViewModel: FavViewModel) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        // call database
        LaunchedEffect(key1 = true) {
            favViewModel.favListMovie()
        }
//        get list
        val movieList = favViewModel.favMovieList.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {

            LazyColumn {
                items(items = movieList.value , key = {it.id}){

                    FavMovieItems(movie = it)

                }
            }
        }

    }



}

@Composable
fun FavMovieItems(movie: FavoriteMovie){


    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(10.dp)) {
            //poster
            AsyncImage(modifier = Modifier
                .fillMaxHeight()
                .width(130.dp), model = movie.poster, contentDescription ="", contentScale = ContentScale.Crop )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
                // title
                Text(text = movie.title, style = MaterialTheme.typography.titleMedium , maxLines = 1, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(5.dp))

                // rate
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = movie.rate, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.height(5.dp))

                // country
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Place, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = movie.country, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.height(5.dp))

                // year
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = movie.year, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.height(5.dp))


                // more info
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "more info", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.tertiary)
                    Icon(imageVector = Icons.Rounded.KeyboardArrowRight, contentDescription ="" , tint = MaterialTheme.colorScheme.tertiary)

                }
                Spacer(modifier = Modifier.height(5.dp))


            }

        }
    }
}