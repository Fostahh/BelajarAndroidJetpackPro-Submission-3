package com.mohammadazri.bajp3rddicodingsubmission.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mohammadazri.bajp3rddicodingsubmission.R
import com.mohammadazri.bajp3rddicodingsubmission.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navController = findNavController(R.id.navHostFragment)
//        val appBarConfiguration = AppBarConfiguration.Builder(
//            setOf(
//                R.id.navigation_movie,
//                R.id.navigation_tvShow,
//            )
//        ).build()
//
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        binding.bottomNavigationView.setupWithNavController(navController)
    }
}