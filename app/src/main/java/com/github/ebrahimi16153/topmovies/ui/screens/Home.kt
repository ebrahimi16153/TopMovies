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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.topmovies.R
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.ui.theme.TopMoviesTheme
import com.github.ebrahimi16153.topmovies.viewModel.HomeViewModel

@Composable
fun Home(navHostController: NavHostController, homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val loading = remember {
        mutableStateOf(false)
    }
    homeViewModel.getMainBannerMovieList(1)

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {


        }
    }


}



@Composable
fun MainBanner(
    list: List<ResponseOfMainBannerMovie.Data>
) {
    TopMoviesTheme {


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.50f), color = MaterialTheme.colorScheme.primary
        ) {


            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                //poster
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.poster),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )

                //Shadow
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color.Transparent, Color(0xC80E0F14), Color(0xFF0e0f14)
                                )
                            )
                        )
                ) {}


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "THE GOD FATHER",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineLarge,
                        fontFamily = FontFamily.SansSerif
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.Star,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "9.3",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.LocationOn,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "9.3",
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodyLarge,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Rounded.DateRange,
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "9.3",
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