package com.github.ebrahimi16153.topmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.models.Result
import com.github.ebrahimi16153.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {

    //first way with Flow
    // data of mainBanner if it success
    private var _mainBannerMovieList = MutableStateFlow<List<ResponseOfMainBannerMovie.Data>>(
        emptyList()
    )
    val mainBannerMovieList: StateFlow<List<ResponseOfMainBannerMovie.Data>> =
        _mainBannerMovieList

    // Error of MainBanner
    private val _mainBannerError = MutableStateFlow<Boolean>(false)
    val mainBannerError = _mainBannerError
    fun getMainBannerMovieList(id: Int) = viewModelScope.launch {

        homeRepository.mainBannerMovie(id).collectLatest { response ->
            when (response) {
                is Result.Error -> {

                    _mainBannerError.update {
                        true
                    }

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


    //second way without Flow
//    private var _mainBannerMovieList = MutableStateFlow<List<ResponseOfMainBannerMovie.Data>>(
//        emptyList()
//    )
//    val mainBannerMovieList: StateFlow<List<ResponseOfMainBannerMovie.Data>> =
//        _mainBannerMovieList
//
//
//    fun getMainBannerMovieList(id: Int) =viewModelScope.launch(){
//
//        val response = homeRepository.mainBanner(id)
//        if (response.data.isNotEmpty()){
//            _mainBannerMovieList.update {
//                response.data
//            }
//        }
//
//
//    }
}