package com.mohammadazri.bajp3rddicodingsubmission.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mohammadazri.bajp3rddicodingsubmission.R
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.ItemsMovieBinding

class FavoriteMovieRecyclerViewAdapter(private val onItemClickCallback: FavoriteMovieRecyclerViewAdapterInterface) : PagedListAdapter<MovieEntity, FavoriteMovieRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.movieId == newItem.movieId

            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        if(movie!= null) {
            holder.bind(movie)
        }
    }

    inner class ViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                textViewMovieTitle.text = movie.title
                textViewMovieReleaseDate.text = movie.releaseDate
                textViewMovieStarNumber.text = movie.rating.toString()
                movie.image?.let {
                    Glide.with(binding.root.context)
                        .load("https://image.tmdb.org/t/p/w500${movie.image}")
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(imageViewMovieImage)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(movie)
                }
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): MovieEntity? = getItem(swipedPosition)

    interface FavoriteMovieRecyclerViewAdapterInterface {
        fun onItemClicked(movie: MovieEntity)
    }
}