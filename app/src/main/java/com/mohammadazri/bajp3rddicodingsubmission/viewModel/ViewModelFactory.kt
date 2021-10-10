package com.mohammadazri.bajp3rddicodingsubmission.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.movie.MovieRepository
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.RepositoryInterface
import com.mohammadazri.bajp3rddicodingsubmission.data.repository.tvShow.TvShowRepository

class ViewModelFactory(private val repositoryInterface: RepositoryInterface) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java)
                    && repositoryInterface is MovieRepository -> {
                return MovieViewModel(repositoryInterface) as T
            }

            modelClass.isAssignableFrom(TvShowViewModel::class.java) && repositoryInterface is TvShowRepository -> {
                return TvShowViewModel(repositoryInterface) as T
            }

            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) && repositoryInterface is MovieRepository -> {
                return DetailMovieViewModel(repositoryInterface) as T
            }

            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) && repositoryInterface is TvShowRepository -> {
                return DetailTvShowViewModel(repositoryInterface) as T
            }

            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) && repositoryInterface is MovieRepository -> {
                return FavoriteMovieViewModel(repositoryInterface) as T
            }

            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) && repositoryInterface is TvShowRepository -> {
                return FavoriteTvShowViewModel(repositoryInterface) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}