package com.github.ebrahimi16153.topmovies.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.github.ebrahimi16153.topmovies.api.ApiServices
import com.github.ebrahimi16153.topmovies.models.ResponseOfMovieList

class MovieListPaging(private val apiServices: ApiServices) :
    PagingSource<Int, ResponseOfMovieList.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ResponseOfMovieList.Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseOfMovieList.Data> {

        return try {
            val currentPage = params.key ?: 1
            val response = apiServices.getLastMovieList(currentPage)
            val nextkey = if (response.data.isNotEmpty()) currentPage + 1 else null
            val prevKey = if (currentPage == 1) null else currentPage - 1

            if (response.data.isNotEmpty()) {
                LoadResult.Page(
                    data = response.data,
                    nextKey = nextkey,
                    prevKey = prevKey
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    nextKey = null,
                    prevKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }


    }
}