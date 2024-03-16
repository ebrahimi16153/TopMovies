package com.github.ebrahimi16153.topmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList
import com.github.ebrahimi16153.topmovies.models.Result
import com.github.ebrahimi16153.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {


    //MainBanner

    //first way with Flow
    // data of mainBanner if it success
    private var _mainBannerMovieList =
        MutableStateFlow<List<ResponseOfMainBannerMovie.Data>>(emptyList())
    val mainBannerMovieList: StateFlow<List<ResponseOfMainBannerMovie.Data>> = _mainBannerMovieList


    // Error of api
    private val _apiError = MutableStateFlow<String>("")
    val apiError = _apiError

    fun getMainBannerMovieList(id: Int) = viewModelScope.launch {

        homeRepository.mainBannerMovie(id).collectLatest { response ->
            when (response) {
                is Result.Error -> {

                    _apiError.value = response.massage

                }

                is Result.Success -> {
                    response.data?.let { responseOfMainBannerMovie ->

                        _mainBannerMovieList.update {
                            responseOfMainBannerMovie.data
                        }
                    }
                }
            }
        }
    }


    // lastMovie
    private val _lastMovieList = MutableStateFlow<List<ResponseOfMovieList.Data>>(emptyList())
    val lastMovieList: StateFlow<List<ResponseOfMovieList.Data>> = _lastMovieList

    fun lastMovie(page: Int) = viewModelScope.launch {

        homeRepository.lastMovie(page).collectLatest { response ->

            when (response) {
                is Result.Error -> {
                    _apiError.value = response.massage
                }

                is Result.Success -> {
                    response.data?.let { responseOfLastMovieList ->
                        _lastMovieList.update {
                            responseOfLastMovieList.data
                        }
                    }
                }
            }

        }

    }


}