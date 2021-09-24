package com.aravindhangs.interview.singleton

import com.aravindhangs.interview.model.ResultsItem

object SingletonPojo {

    private var movies = mutableListOf<ResultsItem>()

    fun setMoviesValueSingleton(movies: List<ResultsItem>) {
        this.movies = movies.toMutableList()
    }

    fun getMoviesValueSingleton(): MutableList<ResultsItem> {
        return movies
    }
}