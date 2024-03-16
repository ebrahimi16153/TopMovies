package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.topmovies.R
import com.github.ebrahimi16153.topmovies.ui.theme.TopMoviesTheme

@Composable
fun Detail(navHostController: NavHostController, movieId: Int?) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

    }
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
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.poster),
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
                                Color.Transparent,
                                MaterialTheme.colorScheme.background,
                            )
                        )
                    )
            )
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(80.dp))
                Image(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                    painter = painterResource(id = R.drawable.poster), contentDescription ="", contentScale = ContentScale.FillWidth )

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
            ){

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "GodFather", color = MaterialTheme.colorScheme.onBackground , style = MaterialTheme.typography.titleLarge, maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center )
                    Spacer(modifier = Modifier.height(5.dp))


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
                                text = "6.3",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {

//                        time
                            Icon(
                                imageVector = Icons.Rounded.LocationOn,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "USA",
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
                                text = "1996",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = FontFamily.SansSerif
                            )
                        }


                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }

            }


        }



    }


}


@Composable
@Preview
fun DetailPreView() {
    TopMoviesTheme {

        Detail(navHostController = rememberNavController(), movieId = 0)

    }


}
