package com.aravindhangs.interview.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aravindhangs.interview.databinding.ActivityMovieDetailBinding
import com.aravindhangs.interview.singleton.SingletonPojo
import com.bumptech.glide.Glide

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private var singleton = SingletonPojo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val position = intent.getIntExtra("position", 0)

        loadUI(position)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    @SuppressLint("SetTextI18n")
    fun loadUI(position: Int) {

        Glide.with(this)
            .load(singleton.getMoviesValueSingleton()[position].multimedia!!.src.toString())
            .into(binding.imagePoster)

        binding.toolbar.title =
            singleton.getMoviesValueSingleton()[position].display_title.toString()

        binding.byline.text =
            "${singleton.getMoviesValueSingleton()[position].byline}"

        if (singleton.getMoviesValueSingleton()[position].mpaa_rating!!.isNotEmpty()) {
            binding.pgRating.text =
                "${singleton.getMoviesValueSingleton()[position].mpaa_rating}"
        } else {
            binding.pgRating.text = " -- "
        }

        binding.headline.text =
            "${singleton.getMoviesValueSingleton()[position].headline}"

        binding.publicationDate.text =
            "${singleton.getMoviesValueSingleton()[position].publication_date}"

        binding.openingDate.text =
            "${singleton.getMoviesValueSingleton()[position].opening_date}"

        binding.dateUpdated.text =
            "${singleton.getMoviesValueSingleton()[position].date_updated}"

        binding.summaryShort.text =
            "${singleton.getMoviesValueSingleton()[position].summary_short}"

        binding.link.text =
            singleton.getMoviesValueSingleton()[position].link!!.suggested_link_text.toString()
        binding.link.paintFlags = binding.link.paintFlags or Paint.UNDERLINE_TEXT_FLAG

        val urlLink = singleton.getMoviesValueSingleton()[position].link!!.url.toString()

        binding.link.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse(urlLink))
            startActivity(browser)
        }
    }
}
