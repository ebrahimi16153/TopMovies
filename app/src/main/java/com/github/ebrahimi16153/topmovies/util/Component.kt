package com.github.ebrahimi16153.topmovies.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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