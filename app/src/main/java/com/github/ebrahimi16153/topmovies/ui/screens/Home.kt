package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.viewModel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navHostController: NavHostController, homeViewModel: HomeViewModel) {
    val loading = remember {
        mutableStateOf(false)
    }
    // cal api
    homeViewModel.getMainBannerMovieList(3)
    // get MainBanner movie list
    val list = homeViewModel.mainBannerMovieList.collectAsState()

    // handel page of Pager
    val pagerState = rememberPagerState(pageCount = { list.value.size })



    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            HorizontalPager(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f),
                state = pagerState
            ) { index ->
                // Our page content
                MainBanner(movie = list.value[index])
            }
            MainBannerIndicator(pagerState = pagerState)
        }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainBannerIndicator(pagerState: PagerState) {
    Row(
        Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
            Box(
                modifier = Modifier
                    .clip(RectangleShape)
                    .background(color)
                    .width(8.dp)
                    .height(2.dp)
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
    }
}


@Composable
fun MainBanner(
    movie: ResponseOfMainBannerMovie.Data
) {


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            //poster
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.poster,
                contentDescription = "",
                contentScale = ContentScale.FillWidth
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
                //title
                Text(
                    text = movie.title!!,
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = FontFamily.SansSerif
                )
                
                Spacer(modifier = Modifier.height(10.dp))
                
                
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
                            text = movie.imdbRating!!,
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
                            text = movie.country!!,
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
                            text = movie.year!!,
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

