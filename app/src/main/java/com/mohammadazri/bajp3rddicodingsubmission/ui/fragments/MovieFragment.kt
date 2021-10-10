package com.mohammadazri.bajp3rddicodingsubmission.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mohammadazri.bajp3rddicodingsubmission.data.source.Status
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.FragmentMovieBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.ui.activities.DetailMovieActivity
import com.mohammadazri.bajp3rddicodingsubmission.ui.adapters.MovieRecyclerViewAdapter
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.MovieViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class MovieFragment : Fragment(),
    MovieRecyclerViewAdapter.MovieRecyclerViewAdapterInterface {

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val factory = ViewModelFactory(Injection.provideMovieRepository(it))
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieRecyclerViewAdapter = MovieRecyclerViewAdapter(this)

            viewModel.getMovies(1).observe(viewLifecycleOwner, { movies ->
                movies.let {
                    when (movies.status) {
                        Status.LOADING -> binding.progressBar1.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            binding.progressBar1.visibility = View.GONE
                            movieRecyclerViewAdapter.submitList(movies.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar1.visibility = View.GONE
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.recyclerViewMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(movie: MovieEntity) {
        val intent = Intent(context, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.movieId)
        startActivity(intent)
    }
}