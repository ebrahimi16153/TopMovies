package com.github.ebrahimi16153.topmovies.ui.screens

import android.widget.ProgressBar
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.topmovies.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun Home(navHostController: NavHostController, homeViewModel: HomeViewModel) {
    val context = LocalContext.current
    val loading = remember {
        mutableStateOf(false)
    }
    homeViewModel.getMainBannerMovieList(1)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

//        LaunchedEffect(key1 = true) {
//            loading.value = homeViewModel.mainBannerError.value
//        }
//
        LaunchedEffect(key1 = true) {
            homeViewModel.getMainBannerMovieList(1)
        }
        homeViewModel.getMainBannerMovieList(1)

        val mainBannerMovieList = homeViewModel.mainBannerMovieList.collectAsState()


        LazyColumn {
            items(items = mainBannerMovieList.value){
                Text(text = it.title!!)
            }
        }

    }








}