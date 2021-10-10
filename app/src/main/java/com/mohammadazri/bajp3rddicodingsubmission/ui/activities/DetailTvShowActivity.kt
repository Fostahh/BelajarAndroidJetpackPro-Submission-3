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
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.ActivityDetailTvShowBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.DetailTvShowViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class DetailTvShowActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    private lateinit var binding: ActivityDetailTvShowBinding
    private var favoriteState = false
    private lateinit var tvShowEntity: TvShowEntity
    private lateinit var viewModel: DetailTvShowViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory(repositoryInterface = Injection.provideDetailTvShowRepository(this))
        viewModel = ViewModelProvider(
            this,
            factory
        )[DetailTvShowViewModel::class.java]
        val tvShowId = intent.getIntExtra(EXTRA_TVSHOW, 0)

        tvShowId.let { id ->
            viewModel.getTvShowById(id).observe(this, {
                it?.let {
                    when(it.status) {
                        Status.LOADING -> {
                            binding.progressBar2.visibility = View.VISIBLE
                            binding.linearLayoutParentTvShow.visibility = View.GONE
                        }
                        Status.SUCCESS -> {
                            binding.progressBar2.visibility = View.GONE
                            binding.linearLayoutParentTvShow.visibility = View.VISIBLE
                            populateTvShow(it)
                        }
                        Status.ERROR -> {
                            binding.progressBar2.visibility = View.GONE
                            Toast.makeText(this, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }

        binding.imageViewFavoriteTvShow.setOnClickListener(this)
    }

    private fun populateTvShow(tvShow: Resource<TvShowEntity>) {
        with(binding) {
            tvShow.data?.let {
                it.image?.let { imageUrl ->
                    Glide.with(applicationContext)
                        .load("https://image.tmdb.org/t/p/w500${imageUrl}")
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(binding.imageViewDetailTvShowImage)
                }
                textViewDetailTvShowTitleValue.text = it.title
                textViewDetailTvShowYearValue.text = it.releaseDate
                textViewDetailTvShowGenreValue.text = it.genres
                val seasonepisodeValue = resources.getString(R.string.season_episode_value, it.seasons, it.episodes)
                textViewDetailTvShowEpisodeSeasonValue.text = seasonepisodeValue
                textViewDetailTvShowStarValue.text = it.rating.toString()
                textViewDetailTvShowSynopsisValue.text = it.overview

                it.isFavorite?.let { state ->
                    if(state) {
                        favoriteState = state
                        setFavoriteIcon(favoriteState)
                    } else {
                        favoriteState = state
                        setFavoriteIcon(favoriteState)
                    }
                }

                tvShowEntity = it
            }
        }
    }

    private fun setFavoriteIcon(state: Boolean) {
        if (state) {
            binding.imageViewFavoriteTvShow.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.imageViewFavoriteTvShow.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.imageViewFavoriteTvShow) {
            if (favoriteState) {
                Toast.makeText(
                    this@DetailTvShowActivity,
                    "Tv Show removed from favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = false
                setFavoriteIcon(favoriteState)
                viewModel.setFavoriteTvShow(tvShowEntity, favoriteState)
            } else {
                Toast.makeText(
                    this@DetailTvShowActivity,
                    "Tv Show added to favorite",
                    Toast.LENGTH_SHORT
                ).show()
                favoriteState = true
                setFavoriteIcon(favoriteState)
                viewModel.setFavoriteTvShow(tvShowEntity, favoriteState)
            }
        }
    }
}