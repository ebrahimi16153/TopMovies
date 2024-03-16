package com.github.ebrahimi16153.topmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.repository.FavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavViewModel @Inject constructor(private val favRepository: FavRepository):ViewModel() {

    private val _favMovieList = MutableStateFlow<List<FavoriteMovie>>(emptyList())
    val favMovieList:StateFlow<List<FavoriteMovie>> = _favMovieList

    fun favListMovie() = viewModelScope.launch {

        favRepository.getFavList().collectLatest { movieList ->

            _favMovieList.update {
                movieList
            }
        }


    }


}