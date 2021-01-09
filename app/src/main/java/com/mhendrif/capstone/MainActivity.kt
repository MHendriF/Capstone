package com.mhendrif.capstone

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mhendrif.capstone.base.BaseActivity
import com.mhendrif.capstone.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

//        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
//        setupWithNavController(binding.bottomNavigationView, navController)
//        setupActionBarWithNavController(
//            navController,
//            AppBarConfiguration.Builder(
//                R.id.navigation_movie,
//                R.id.navigation_tv_show,
//                R.id.navigation_favorite
//            ).build()
//        )

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()
}