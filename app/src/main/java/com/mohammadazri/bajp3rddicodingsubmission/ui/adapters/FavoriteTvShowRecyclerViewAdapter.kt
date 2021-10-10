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
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.ItemsTvshowBinding

class FavoriteTvShowRecyclerViewAdapter(private val onItemClickCallback: FavoriteTvShowRecyclerViewAdapterInterface) :
    PagedListAdapter<TvShowEntity, FavoriteTvShowRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvShowId == newItem.tvShowId

            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tvShow = getItem(position)
        tvShow?.let {
            holder.bind(tvShow)
        }
    }

    inner class ViewHolder(private val binding: ItemsTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
            with(binding) {
                textViewTvShowTitle.text = tvShow.title
                textViewTvShowReleaseDate.text = tvShow.releaseDate
                textViewTvShowStarNumber.text = tvShow.rating.toString()
                tvShow.image?.let {
                    Glide.with(binding.root.context)
                        .load("https://image.tmdb.org/t/p/w500${tvShow.image}")
                        .apply(
                            RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)
                        )
                        .into(imageViewTvShowImage)
                }
                itemView.setOnClickListener {
                    onItemClickCallback.onItemClicked(tvShow)
                }
            }
        }
    }

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)

    interface FavoriteTvShowRecyclerViewAdapterInterface {
        fun onItemClicked(tvShow: TvShowEntity)
    }

}