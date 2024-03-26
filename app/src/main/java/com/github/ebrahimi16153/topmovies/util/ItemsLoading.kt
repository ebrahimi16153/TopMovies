package com.github.ebrahimi16153.topmovies.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ItemsLoading(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(), contentAlignment = Alignment.Center){

        CircularProgressIndicator(Modifier.size(50.dp))


    }
}