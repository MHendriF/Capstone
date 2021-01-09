package com.mhendrif.capstone

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.mhendrif.capstone.base.BaseActivity
import com.mhendrif.capstone.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        setupActionBarWithNavController(
            navController,
            AppBarConfiguration.Builder(
                R.id.navigation_movie,
                R.id.navigation_tv_show,
                R.id.navigation_favorite
            ).build()
        )
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}