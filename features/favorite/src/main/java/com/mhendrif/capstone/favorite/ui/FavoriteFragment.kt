package com.mhendrif.capstone.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.mhendrif.capstone.core.di.CoreComponent
import com.mhendrif.capstone.core.di.DaggerCoreComponent
import com.mhendrif.capstone.favorite.R
import com.mhendrif.capstone.favorite.databinding.FragmentFavoriteBinding
import com.mhendrif.capstone.favorite.di.DaggerFavoriteComponent
import com.mhendrif.capstone.ui.base.BaseFragment

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2
        val viewPager = binding.viewPager

        // TabLayout
        val tabLayout = binding.tabs

        viewPager.adapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onResume() {
        super.onResume()
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() {
        val bottomNav: BottomNavigationView = requireActivity().findViewById(com.mhendrif.capstone.R.id.bottom_navigation_view)
        bottomNav.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        // ViewPager2
        val viewPager = binding.viewPager
        // TabLayout
        val tabLayout = binding.tabs
        viewPager.adapter = null
        super.onDestroyView()
    }
}
