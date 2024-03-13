package com.github.ebrahimi16153.topmovies.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun gradient(isVerticalGradient:Boolean,colors:List<Color>): Brush {

    val start =
        if (isVerticalGradient)
            Offset(0f,Float.POSITIVE_INFINITY)
        else
            Offset(Float.POSITIVE_INFINITY,0f)

return Brush.linearGradient(colors = colors , start = start )

}