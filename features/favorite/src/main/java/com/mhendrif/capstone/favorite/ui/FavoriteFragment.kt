package com.mhendrif.capstone.favorite.ui

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mhendrif.capstone.di.FavoriteModuleDependencies
import com.mhendrif.capstone.favorite.R
import com.mhendrif.capstone.favorite.databinding.FragmentFavoriteBinding
import com.mhendrif.capstone.favorite.di.DaggerFavoriteComponent
import com.mhendrif.capstone.ui.base.BaseFragment
import dagger.hilt.android.EntryPointAccessors

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDynamicFeatureModule()
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNavigation()
    }

    private fun injectDynamicFeatureModule() {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().application,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun setUpBottomNavigation() {
        val bottomNav: BottomNavigationView = requireActivity().findViewById(com.mhendrif.capstone.R.id.bottom_navigation_view)
        bottomNav.visibility = View.VISIBLE
    }
}
