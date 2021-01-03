package com.mhendrif.capstone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mhendrif.capstone.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = Navigation.findNavController(this, R.id.nav_host_main_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(
                R.id.fragment_movie,
                R.id.fragment_tv_show,
                R.id.fragment_favorite
            ).build()
        )
    }
}