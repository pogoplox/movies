package com.example.moviecompose.data.model

import com.squareup.moshi.Json

class PagResponse<T>(
    val page: Int,
    val results: List<T>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)