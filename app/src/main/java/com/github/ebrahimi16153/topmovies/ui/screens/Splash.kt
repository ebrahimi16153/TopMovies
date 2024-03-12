package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.ebrahimi16153.topmovies.navigation.ScreenRoute
import kotlinx.coroutines.delay

@Composable
fun Splash(navHostController: NavHostController){

    Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center){



        LaunchedEffect(key1 = true) {
            delay(1000)
            navHostController.navigate(ScreenRoute.Home.name)
        }

    }

}