@file:Suppress("UNCHECKED_CAST")

package com.aravindhangs.interview.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aravindhangs.interview.repo.MovieRepository

class MyViewModelFactory constructor(private val repository: MovieRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainMovieViewModel::class.java)) {
            MainMovieViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}