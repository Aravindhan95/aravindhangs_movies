package com.aravindhangs.interview.repo

import com.aravindhangs.interview.api.RetrofitService

class MovieRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllMovies() = retrofitService.getAllMovies(
        "by-opening-date",
        "WOm3aya6ZO7dpN8RkAOTU5yOlWzZfR8O"
    )
}