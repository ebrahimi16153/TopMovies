package com.github.ebrahimi16153.topmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.FavoriteMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieDetail
import com.github.ebrahimi16153.topmovies.models.Result
import com.github.ebrahimi16153.topmovies.repository.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val detailsRepository: DetailRepository) :
    ViewModel() {

     var _detailMovie =
        MutableStateFlow<ResponseOfMovieDetail?>(null)
    val detailMovie: StateFlow<ResponseOfMovieDetail?> = _detailMovie


    // Error of api
    private val _apiError = MutableStateFlow<String>("")
    val apiError = _apiError

    fun getDetailMovie(id: Int) = viewModelScope.launch {

        detailsRepository.movieDetail(id).collectLatest { response ->
            when (response) {
                is Result.Error -> {

                    _apiError.value = response.massage

                }

                is Result.Success -> {
                    response.data?.let { responseOfDetailMovie ->

                        _detailMovie.update {
                            responseOfDetailMovie
                        }
                    }
                }
            }
        }
    }


    private var _movieExists =
        MutableStateFlow<Boolean>(false)
    val movieExists: StateFlow<Boolean> = _movieExists


    fun movieExists(id: Int) = viewModelScope.launch {

        detailsRepository.existsMovie(id).collectLatest { response ->
            _movieExists.update {
                response
            }
        }
    }



//    addFav

    fun addFav(favoriteMovie: FavoriteMovie) = viewModelScope.launch(Dispatchers.IO) {

        detailsRepository.addFav(favoriteMovie)

    }

    // delete fav
    fun deleteFav(favoriteMovie: FavoriteMovie) = viewModelScope.launch(Dispatchers.IO) {

        detailsRepository.deleteFav(favoriteMovie)

    }
}