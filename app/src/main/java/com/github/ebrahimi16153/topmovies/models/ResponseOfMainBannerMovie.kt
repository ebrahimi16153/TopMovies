package com.github.ebrahimi16153.topmovies.models


import com.google.gson.annotations.SerializedName

data class ResponseOfMainBannerMovie(
    @SerializedName("data")
    val `data`: List<Data> = emptyList(),
    @SerializedName("metadata")
    val metadata: Metadata =ResponseOfMainBannerMovie.Metadata()
) {
    data class Data(
        @SerializedName("country")
        val country: String?,
        @SerializedName("genres")
        val genres: List<String?>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("images")
        val images: List<String?>?,
        @SerializedName("imdb_rating")
        val imdbRating: String?,
        @SerializedName("poster")
        val poster: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("year")
        val year: String?
    )

    data class Metadata(
        @SerializedName("current_page")
        val currentPage: String ="",
        @SerializedName("page_count")
        val pageCount: Int = 0,
        @SerializedName("per_page")
        val perPage: Int = 0,
        @SerializedName("total_count")
        val totalCount: Int =0
    )
}