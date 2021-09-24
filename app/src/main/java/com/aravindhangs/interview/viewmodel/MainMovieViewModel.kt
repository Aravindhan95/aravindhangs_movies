package com.aravindhangs.interview.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.aravindhangs.interview.adapter.RecyclerItemClickListener
import com.aravindhangs.interview.model.Movie
import com.aravindhangs.interview.repo.MovieRepository
import com.aravindhangs.interview.ui.MovieDetailActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainMovieViewModel constructor(private val repository: MovieRepository) : ViewModel() {

    val movieList = MutableLiveData<Movie>()
    val errorMessage = MutableLiveData<String>()

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }

    fun callDetailsPage(context: Context, recyclerView: RecyclerView) {
        recyclerView.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                recyclerView,
                object : RecyclerItemClickListener.OnItemClickListener {

                    override fun onItemClick(view: View, position: Int) {
                        val intent = Intent(context, MovieDetailActivity::class.java)
                        intent.putExtra("position", position)
                        context.startActivity(intent)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        TODO("do nothing")
                    }
                })
        )
    }
}