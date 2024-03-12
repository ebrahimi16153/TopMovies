package com.github.ebrahimi16153.topmovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ebrahimi16153.topmovies.models.ResponseOfMainBannerMovie
import com.github.ebrahimi16153.topmovies.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {


  private var _mainBannerMovieList = MutableStateFlow<List<ResponseOfMainBannerMovie.Data>>(
      emptyList()
    )

     val mainBannerMovieList :StateFlow<List<ResponseOfMainBannerMovie.Data>> = _mainBannerMovieList


    fun getMainBannerMovieList(id:Int) = viewModelScope.launch {
       homeRepository.mainBannerMovie(id).collect{ response ->
           _mainBannerMovieList.update {
               response.data
           }

       }
    }




}