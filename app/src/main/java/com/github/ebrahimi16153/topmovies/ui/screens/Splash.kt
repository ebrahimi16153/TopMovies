package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.topmovies.R
import com.github.ebrahimi16153.topmovies.navigation.ScreenRoute
import kotlinx.coroutines.delay

@Composable
fun Splash(navHostController: NavHostController){

    Box(modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colorScheme.background),contentAlignment = Alignment.Center){

            Image(modifier = Modifier.size(128
                .dp), painter = painterResource(id = R.drawable.logo), contentDescription ="Logo")

        LaunchedEffect(key1 = true) {
            delay(1000)
            navHostController.popBackStack()
            navHostController.navigate(ScreenRoute.Home.name)
        }

    }

}

@Preview
@Composable
fun SplashPreview(){
    Splash(navHostController = rememberNavController())
}