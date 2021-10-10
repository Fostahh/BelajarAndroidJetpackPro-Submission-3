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
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.FragmentFavoriteMovieBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.ui.activities.DetailMovieActivity
import com.mohammadazri.bajp3rddicodingsubmission.ui.adapters.FavoriteMovieRecyclerViewAdapter
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.FavoriteMovieViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class FavoriteMovieFragment : Fragment(),
    FavoriteMovieRecyclerViewAdapter.FavoriteMovieRecyclerViewAdapterInterface {

    private lateinit var binding: FragmentFavoriteMovieBinding
    private lateinit var viewModel: FavoriteMovieViewModel
    private lateinit var favoriteMovieRecyclerViewAdapter: FavoriteMovieRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.recyclerViewFavoriteMovie)

        activity?.let {
            val factory = ViewModelFactory(Injection.provideMovieRepository(it))
            viewModel = ViewModelProvider(this, factory)[FavoriteMovieViewModel::class.java]
            favoriteMovieRecyclerViewAdapter = FavoriteMovieRecyclerViewAdapter(this)

            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, {
                it.let {
                    favoriteMovieRecyclerViewAdapter.submitList(it)
                }
            })

            with(binding.recyclerViewFavoriteMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(movie: MovieEntity) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
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
                val movieEntity = favoriteMovieRecyclerViewAdapter.getSwipedData(swipedPosition)
                movieEntity?.let {
                    viewModel.setFavoriteMovie(it, false)
                }
                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { _ ->
                    movieEntity?.let { viewModel.setFavoriteMovie(it, true) }
                }
                snackbar.show()
            }
        }
    })
}