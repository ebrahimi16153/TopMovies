package com.github.ebrahimi16153.topmovies.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import com.github.ebrahimi16153.topmovies.models.Result
import com.github.ebrahimi16153.topmovies.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository):ViewModel() {


    val searchQuery : MutableState<String> = mutableStateOf("")

    // searchMovie
    private val _searchMovieList = MutableStateFlow<List<ResponseOfMovieList.Data>>(emptyList())
    val searchMovieList: StateFlow<List<ResponseOfMovieList.Data>> = _searchMovieList


    // Error of api
    private val _apiError = MutableStateFlow<String>("")
    val apiError = _apiError

    fun searchMovie(q:String ) = viewModelScope.launch {

        searchRepository.searchMovie(q).collectLatest { response ->

            when (response) {
                is Result.Error -> {
                    _apiError.value = response.massage
                }

                is Result.Success -> {
                    response.data?.let { responseOfLastMovieList ->
                        _searchMovieList.update {
                            responseOfLastMovieList.data
                        }
                    }
                }
            }

        }

    }


}