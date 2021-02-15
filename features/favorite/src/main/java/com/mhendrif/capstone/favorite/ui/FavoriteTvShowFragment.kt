package com.mhendrif.capstone.favorite.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.core.util.ItemListener
import com.mhendrif.capstone.di.FavoriteModuleDependencies
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.favorite.R
import com.mhendrif.capstone.favorite.databinding.FragmentFavoriteTvShowBinding
import com.mhendrif.capstone.favorite.di.DaggerFavoriteComponent
import com.mhendrif.capstone.favorite.di.FavoriteViewModelFactory
import com.mhendrif.capstone.ui.base.BaseFragment
import com.mhendrif.capstone.util.AutoClearedValue
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowFragment :
    BaseFragment<FragmentFavoriteTvShowBinding>(R.layout.fragment_favorite_tv_show),
    ItemListener<TvShow> {

    companion object {
        private const val ARG_SECTION_NUMBER = Constants.ARG_FAVORITE_TV
        fun newInstance(index: Int) = FavoriteTvShowFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SECTION_NUMBER, index) }
        }
    }

    @Inject
    lateinit var factory: FavoriteViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
    private var adapter by AutoClearedValue<FavoriteTvShowAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDynamicFeatureModule()
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            adapter = FavoriteTvShowAdapter().apply {
                onItemListener = this@FavoriteTvShowFragment
                binding.rvTvShow.setHasFixedSize(true)
                binding.rvTvShow.adapter = this
            }
            favoriteViewModel.tvShows.observe(viewLifecycleOwner, { handleStat(it) })
            favoriteViewModel.getFavoriteTvShows()
        }
    }

    private fun handleStat(resource: List<TvShow>) {
        with(binding) {
            if (resource.isNotEmpty()) {
                isLoading = false
                viewDataEmpty.isEmptyData = false
                rvTvShow.visibility = View.VISIBLE
                adapter.submitList(resource)
            } else {
                isLoading = false
                viewDataEmpty.isEmptyData = true
                rvTvShow.visibility = View.GONE
            }
        }
    }

    private fun navigateToDetail(model: TvShow) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvShowFragment(model)
        )
    }

    override fun onItemClick(model: TvShow) {
        navigateToDetail(model)
    }

    override fun onResume() {
        super.onResume()
        val actionBar: ActionBar? = (activity as MainActivity?)?.supportActionBar
        actionBar?.apply {
            title = getString(R.string.favorite)
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.by_name -> {
                item.isChecked = true
                favoriteViewModel.sorting(SortOrder.BY_NAME); true
            }
            R.id.by_release -> {
                item.isChecked = true
                favoriteViewModel.sorting(SortOrder.BY_DATE); true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
}
