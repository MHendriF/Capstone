package com.mhendrif.capstone

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.mhendrif.capstone.databinding.ActivityMainBinding
import com.mhendrif.capstone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        binding.bottomNavigationView.visibility = View.VISIBLE
    }
}
