package com.mohammadazri.bajp3rddicodingsubmission.ui.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohammadazri.bajp3rddicodingsubmission.R
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Resource
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Status
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.ActivityDetailMovieBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.DetailMovieViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private var favoriteState = false
    private lateinit var movieEntity: MovieEntity
    private lateinit var viewModel : DetailMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory =
            ViewModelFactory(repositoryInterface = Injection.provideDetailMovieRepository(this))
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailMovieViewModel::class.java]

        val movieId = intent.getIntExtra(EXTRA_MOVIE, 0)
        movieId.let { id ->
            viewModel.getMovieById(id).observe(this, {
                it.let {
                    when (it.status) {
//                        Status.LOADING -> {
//                            binding.progressBar3.visibility = View.VISIBLE
//                            binding.linearLayoutParentMovie.visibility = View.GONE
//                        }
                        Status.SUCCESS -> {
                            binding.progressBar3.visibility = View.GONE
                            binding.linearLayoutParentMovie.visibility = View.VISIBLE
                            populateMovie(it)
                        }
//                        Status.ERROR -> {
//                            binding.progressBar3.visibility = View.GONE
//                            Toast.makeText(this, "There is an error", Toast.LENGTH_SHORT).show()
//                        }
                    }
                }
            })
        }

        binding.imageViewFavoriteMovie.setOnClickListener(this)
    }

    private fun populateMovie(movie: Resource<MovieEntity>) {
        with(binding) {
            movie.data?.let {
                it.image?.let { imageUrl ->
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${imageUrl}")
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(binding.imageViewDetailMovieImage)
                }
                textViewDetailMovieTitleValue.text = it.title
                textViewDetailMovieYearValue.text = it.releaseDate
                textViewDetailMovieGenreValue.text = it.genres
                val durationValue = resources.getString(R.string.duration_minutes, it.runtime)
                textViewDetailMovieDurationValue.text = durationValue
                textViewDetailMovieStarValue.text = it.rating.toString()
                textViewDetailMovieSynopsisValue.text = it.overview
                it.isFavorite?.let { state ->
                    if (state) {
                        favoriteState = state
                        setFavoriteIcon(favoriteState)
                    } else {
                        favoriteState = state
                        setFavoriteIcon(state)
                    }
                }
                movieEntity = it
            }
        }
    }


    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            binding.imageViewFavoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageViewFavoriteMovie.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imageViewFavoriteMovie) {
            if (favoriteState) {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "Movie removed from favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = false
                setFavoriteIcon(favoriteState)
                viewModel.setFavoriteMovie(movieEntity, favoriteState)
            } else {
                Toast.makeText(
                    this@DetailMovieActivity,
                    "Movie added to favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = true
                setFavoriteIcon(favoriteState)
                viewModel.setFavoriteMovie(movieEntity, favoriteState)

            }
        }
    }

}