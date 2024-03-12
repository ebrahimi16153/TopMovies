package com.github.ebrahimi16153.topmovies.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.github.ebrahimi16153.topmovies.ui.screens.Favorite
import com.github.ebrahimi16153.topmovies.ui.screens.Home
import com.github.ebrahimi16153.topmovies.ui.screens.Search
import com.github.ebrahimi16153.topmovies.ui.screens.Splash

@Composable
fun MainScaffold(
    navController: NavHostController
) {

    val navBarVisibility = remember {
        mutableStateOf(false)
    }
    val currentDestination = remember {
        mutableStateOf("")
    }

    // save name of currentDestination every time navController change Screen
    navController.addOnDestinationChangedListener { _, destination, _ ->

        navBarVisibility.value =
            destination.route.equals(ScreenRoute.Home.name) || destination.route.equals(ScreenRoute.Search.name) || destination.route.equals(
                ScreenRoute.Favorite.name
            )

        currentDestination.value = destination.route.toString()

    }


    // scaffold

    Scaffold(

        // Bottom NavigationBar -> just show in Home,Search and Favorite
        bottomBar = {
            if (navBarVisibility.value) {
                BottomBar(
                    navController = navController,
                    currentDestination = currentDestination.value
                )
            }
        }


    )
    {
        Surface(modifier = Modifier.padding(it)) {

           //navigation
            NavHost(navController = navController, startDestination = ScreenRoute.Splash.name) {

                //splash
                composable(ScreenRoute.Splash.name) {
                    Splash(navHostController = navController)
                }

                //  home
                composable(ScreenRoute.Home.name) {
                    Home(navHostController = navController)
                }

                // search
                composable(ScreenRoute.Search.name) {
                    Search(navHostController = navController)
                }

                //favorite
                composable(ScreenRoute.Favorite.name) {
                    Favorite(navHostController = navController)
                }


            }


        }
    }


}



//BottomNavigationBar
@Composable
fun BottomBar(navController: NavHostController, currentDestination: String) {

    NavigationBar {
        NavigationBarItem(selected = currentDestination == "Home", onClick = {
            navController.navigate(ScreenRoute.Home.name)

        }, icon = {
            Icon(
                imageVector = Icons.Rounded.Home,
                contentDescription = "home"
            )
        }, label = {
            Text(text = "Home")
        })

        NavigationBarItem(selected = currentDestination == ScreenRoute.Search.name, onClick = {

            navController.navigate(ScreenRoute.Search.name)

        }, icon = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = "search"
            )
        }, label = {
            Text(text = "Search")
        })

        NavigationBarItem(selected = currentDestination == ScreenRoute.Favorite.name, onClick = {

            navController.navigate(ScreenRoute.Favorite.name)


        }, icon = {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "favorite"
            )
        }, label = {
            Text(text = "Favorite")
        })

    }


}
