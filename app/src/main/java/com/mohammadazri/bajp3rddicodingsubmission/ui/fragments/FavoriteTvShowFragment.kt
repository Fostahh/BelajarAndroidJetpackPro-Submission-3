package com.mohammadazri.bajp3rddicodingsubmission.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mohammadazri.bajp3rddicodingsubmission.R
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.FragmentFavoriteTvShowBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.ui.activities.DetailTvShowActivity
import com.mohammadazri.bajp3rddicodingsubmission.ui.adapters.FavoriteTvShowRecyclerViewAdapter
import com.mohammadazri.bajp3rddicodingsubmission.ui.adapters.TvShowRecyclerViewAdapter
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.FavoriteTvShowViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class FavoriteTvShowFragment : Fragment(),
    FavoriteTvShowRecyclerViewAdapter.FavoriteTvShowRecyclerViewAdapterInterface {

    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var viewModel: FavoriteTvShowViewModel
    private lateinit var favoriteTvShowRecyclerViewAdapter: FavoriteTvShowRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavoriteTvShow)

        activity?.let {
            val factory = ViewModelFactory(Injection.provideTvShowRepository(it))
            viewModel = ViewModelProvider(this, factory)[FavoriteTvShowViewModel::class.java]
            favoriteTvShowRecyclerViewAdapter = FavoriteTvShowRecyclerViewAdapter(this)

            viewModel.getFavoriteTvShow().observe(viewLifecycleOwner, {
                it.let {
                    favoriteTvShowRecyclerViewAdapter.submitList(it)
                }
            })

            with(binding.recyclerViewFavoriteTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(tvShow: TvShowEntity) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow.tvShowId)
        startActivity(intent)
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val tvShowEntity = favoriteTvShowRecyclerViewAdapter.getSwipedData(swipedPosition)
                tvShowEntity?.let {
                    viewModel.setFavoriteTvShow(it, false)
                }
                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    tvShowEntity?.let { viewModel.setFavoriteTvShow(it, true) }
                }
                snackbar.show()
            }
        }
    })
}