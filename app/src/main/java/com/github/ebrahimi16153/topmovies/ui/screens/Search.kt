package com.github.ebrahimi16153.topmovies.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.ui.unit.dp
import com.github.ebrahimi16153.topmovies.util.MovieItems
import com.github.ebrahimi16153.topmovies.viewModel.SearchViewModel


@Composable
fun Search(navHostController: NavHostController, searchViewModel: SearchViewModel) {
    


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(10.dp))
            MySearchBar(value = searchViewModel.searchQuery.value) {
                searchViewModel.searchQuery.value = it
                //call api
                searchViewModel.searchMovie(searchViewModel.searchQuery.value)

            }
            val searchMovieList = searchViewModel.searchMovieList.collectAsState()

            LazyColumn {
                items(searchMovieList.value, key = {movie ->
                    movie.id!!
                }){
                    MovieItems(movie = it)
                }
            }
        }


        
        
        
    }

}

@Composable
fun MySearchBar(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
        value = value,
        onValueChange ={
                       onValueChange(it)
        },
        singleLine = true,
        label = {
            Text(
                text = "Search"
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground,
//            focusedLabelColor = MaterialTheme.colorScheme.onBackground,

            
        )
    )
}

