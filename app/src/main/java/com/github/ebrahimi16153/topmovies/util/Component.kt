package com.github.ebrahimi16153.topmovies.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.topmovies.R

@Composable
fun Loading(){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        CircularProgressIndicator(
            strokeWidth = 2.dp,
            modifier = Modifier.width(50.dp),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.onBackground,
        )
    }


}


@Composable
fun Error(value:String){

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

      Text(text = value)
    }
}


@Preview
@Composable
fun MovieItems(){


    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(180.dp).padding(10.dp)) {
            //poster
            Image(modifier = Modifier
                .fillMaxHeight()
                .width(130.dp), painter = painterResource(id = R.drawable.poster), contentDescription ="", contentScale = ContentScale.Crop )
            Spacer(modifier = Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
               // title
                Text(text = "THE GODFATHER", style = MaterialTheme.typography.titleMedium , maxLines = 1, overflow = TextOverflow.Ellipsis, color = MaterialTheme.colorScheme.onBackground)
                Spacer(modifier = Modifier.height(5.dp))

                // rate
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Star, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "9.1", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.height(5.dp))

                // country
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.Place, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "USA", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
                }
                Spacer(modifier = Modifier.height(5.dp))

                // year
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription ="" , tint = MaterialTheme.colorScheme.onBackground)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(text = "USA", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onBackground)
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