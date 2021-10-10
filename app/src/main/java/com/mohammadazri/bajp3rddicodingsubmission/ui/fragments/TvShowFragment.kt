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
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity
import com.mohammadazri.bajp3rddicodingsubmission.databinding.FragmentTvShowBinding
import com.mohammadazri.bajp3rddicodingsubmission.di.Injection
import com.mohammadazri.bajp3rddicodingsubmission.ui.activities.DetailTvShowActivity
import com.mohammadazri.bajp3rddicodingsubmission.ui.adapters.TvShowRecyclerViewAdapter
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.TvShowViewModel
import com.mohammadazri.bajp3rddicodingsubmission.viewModel.ViewModelFactory

class TvShowFragment : Fragment(), TvShowRecyclerViewAdapter.TvShowRecyclerViewAdapterInterface {

    private lateinit var binding: FragmentTvShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            val factory = ViewModelFactory(Injection.provideTvShowRepository(it))
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]
            val tvShowRecyclerViewAdapter = TvShowRecyclerViewAdapter(this)

            viewModel.getTvShows(1).observe(viewLifecycleOwner, {
                it.let {
                    when (it.status) {
                        Status.LOADING -> {
                            binding.progressBar4.visibility = View.VISIBLE
                        }
                        Status.SUCCESS -> {
                            binding.progressBar4.visibility = View.GONE
                            tvShowRecyclerViewAdapter.submitList(it.data)
                        }
                        Status.ERROR -> {
                            binding.progressBar4.visibility = View.GONE
                            Toast.makeText(context, "There is an error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(binding.recyclerViewTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowRecyclerViewAdapter
            }
        }
    }

    override fun onItemClicked(tvShow: TvShowEntity) {
        val intent = Intent(context, DetailTvShowActivity::class.java)
        intent.putExtra(DetailTvShowActivity.EXTRA_TVSHOW, tvShow.tvShowId)
        startActivity(intent)
    }
}