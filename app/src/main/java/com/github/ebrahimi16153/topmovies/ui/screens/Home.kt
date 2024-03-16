package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import com.github.ebrahimi16153.topmovies.navigation.ScreenRoute
import com.github.ebrahimi16153.topmovies.util.Constant
import com.github.ebrahimi16153.topmovies.util.Error
import com.github.ebrahimi16153.topmovies.util.Loading
import com.github.ebrahimi16153.topmovies.util.MovieItems
import com.github.ebrahimi16153.topmovies.viewModel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navHostController: NavHostController, homeViewModel: HomeViewModel) {

    // call api
    homeViewModel.getMainBannerMovieList(3)
    homeViewModel.lastMovie(page = 1)
    // get MainBanner movie list
    val mainBannerList = homeViewModel.mainBannerMovieList.collectAsState()
    val lastMovieList = homeViewModel.lastMovieList.collectAsState()

    // if api can't create response -> error massage must show to user  -> if list going wrong -> val error is fill
    val error = homeViewModel.apiError.collectAsState()

    // handel page of Pager
    val pagerState = rememberPagerState(pageCount = { mainBannerList.value.size })



    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {

        // check error is empty
        if (error.value.isEmpty()) {

            // check list is ready or not
            if (mainBannerList.value.isEmpty() && lastMovieList.value.isEmpty()) {

                //  if list is not ready show a loading
                Loading()
            } else {

                // if list is ready show content (HomeContent)
                Column(
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Pager(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(500.dp),
                        pagerState = pagerState,
                        list = mainBannerList
                    )

                    LastMovie(lastMovies = lastMovieList.value,navController = navHostController)

                }
            }
        } else {
            //if error is not empty show error
            Error(value = error.value)
        }
    }

}


@Composable
fun LastMovie(lastMovies: List<ResponseOfMovieList.Data>,navController: NavHostController) {

    lastMovies.forEach { movie ->
        Column {
            MovieItems(movie = movie,onItemClick = {
                navController.navigate(route = "${ScreenRoute.Detail.name}/${movie.id}")
            })
        }
    }


}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun Pager(
    pagerState: PagerState,
    list: State<List<ResponseOfMainBannerMovie.Data>>,
    modifier: Modifier = Modifier
) {

    HorizontalPager(
        modifier = modifier,
        state = pagerState
    ) { index ->
        // Our page content
            MainBanner(modifier = Modifier.fillMaxSize(), movie = list.value[index])
    }
    MainBannerIndicator(pagerState = pagerState)



}

@Composable
fun MainBanner(
    modifier: Modifier = Modifier,
    movie: ResponseOfMainBannerMovie.Data
) {

    Box(
        modifier = modifier,
        Alignment.BottomCenter
    ) {


        //poster
        AsyncImage(
            modifier = modifier,
            model = movie.poster,
            contentDescription = "",
            contentScale = ContentScale.FillWidth
        )

        //Shadow
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
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
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Clip,
                text = movie.title!!,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainBannerIndicator(pagerState: PagerState) {
    Row(
        Modifier.height(10.dp)
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
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



