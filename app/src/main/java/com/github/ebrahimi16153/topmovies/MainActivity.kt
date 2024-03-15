package com.github.ebrahimi16153.topmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.github.ebrahimi16153.topmovies.navigation.MainScaffold
import com.github.ebrahimi16153.topmovies.ui.theme.TopMoviesTheme
import com.github.ebrahimi16153.topmovies.viewModel.HomeViewModel
import com.github.ebrahimi16153.topmovies.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel:HomeViewModel by viewModels()
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TopMoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   val navController = rememberNavController()
                    MainScaffold(navController = navController, homeViewModel = homeViewModel,searchViewModel = searchViewModel)
                }
            }
        }
    }
}
