package com.aravindhangs.interview.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.aravindhangs.interview.adapter.MainAdapter
import com.aravindhangs.interview.api.RetrofitService
import com.aravindhangs.interview.databinding.ActivityMovieListBinding
import com.aravindhangs.interview.repo.MovieRepository
import com.aravindhangs.interview.singleton.SingletonPojo
import com.aravindhangs.interview.viewmodel.MainMovieViewModel
import com.aravindhangs.interview.viewmodel.MyViewModelFactory

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding

    private lateinit var viewModel: MainMovieViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MovieRepository(retrofitService))).get(
                MainMovieViewModel::class.java
            )

        binding.recyclerview.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        viewModel.movieList.observe(this, {
            binding.progressBar.visibility = View.GONE
            adapter.setMovieList(it.results!!)
            SingletonPojo.setMoviesValueSingleton(it.results)
        })

        viewModel.errorMessage.observe(this, {
            binding.progressBar.visibility = View.GONE
        })

        viewModel.getAllMovies()

        viewModel.callDetailsPage(this, binding.recyclerview)
    }
}