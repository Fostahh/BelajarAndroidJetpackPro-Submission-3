package com.mohammadazri.bajp3rddicodingsubmission.ui.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mohammadazri.bajp3rddicodingsubmission.ui.fragments.MovieFragment
import com.mohammadazri.bajp3rddicodingsubmission.ui.fragments.TvShowFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }
}