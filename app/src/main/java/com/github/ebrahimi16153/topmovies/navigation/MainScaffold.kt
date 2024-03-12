package com.github.ebrahimi16153.topmovies.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainScaffold(
    navController: NavHostController
) {

    Scaffold(

        bottomBar = {
            BottomBar(navController = navController)
        }

    )
    {
        Surface(modifier = Modifier.padding(it)) {

            NavHost(navController = navController, startDestination = ScreenRoute.Home.name) {





            }


        }
    }


}


@Composable
fun BottomBar(navController: NavHostController) {

    NavigationBar {
        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "home"
            )
        }, label = {
            Text(text = "Home")
        })

        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "search"
            )
        }, label = {
            Text(text = "Search")
        })

        NavigationBarItem(selected = false, onClick = { /*TODO*/ }, icon = {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "favorite"
            )
        }, label = {
            Text(text = "Favorite")
        })

    }


}
