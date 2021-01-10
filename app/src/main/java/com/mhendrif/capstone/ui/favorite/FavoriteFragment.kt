package com.mhendrif.capstone.ui.favorite

import android.content.Context
import android.os.Bundle
import android.view.*
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ui.ViewModelFactory
import com.mhendrif.capstone.ui.base.BaseFragment
import com.mhendrif.capstone.ui.FavoritePagerAdapter
import com.mhendrif.capstone.databinding.FragmentFavoriteBinding
import javax.inject.Inject

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    @Inject
    internal lateinit var factory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = FavoritePagerAdapter(requireContext(), childFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}