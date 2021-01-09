package com.mhendrif.capstone.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.base.BaseFragment
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.core.utils.ItemListener
import com.mhendrif.capstone.databinding.FragmentFavoriteTvShowBinding
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.ui.TvShowAdapter
import com.mhendrif.capstone.domain.model.TvShow
import javax.inject.Inject

class FavoriteTvShowFragment : BaseFragment<FragmentFavoriteTvShowBinding>(R.layout.fragment_favorite_tv_show),
    ItemListener<TvShow> {

    companion object {
        private const val ARG_SECTION_NUMBER = "FAVORITE_TV_FRAGMENT"

        fun newInstance(index: Int) = FavoriteTvShowFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SECTION_NUMBER, index) }
        }
    }

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
    private lateinit var adapter: TvShowAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            adapter = TvShowAdapter().apply {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sort_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.by_name -> {
                item.isChecked = true
                favoriteViewModel.sorting(SortOrder.BY_NAME);true
            }
            R.id.by_release -> {
                item.isChecked = true
                favoriteViewModel.sorting(SortOrder.BY_DATE);true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun navigateToDetail(model: TvShow) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailTvShowFragment(model)
        )
    }

    override fun onItemClick(model: TvShow) {
        navigateToDetail(model)
    }
}