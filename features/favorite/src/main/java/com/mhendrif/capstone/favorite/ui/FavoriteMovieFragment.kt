package com.mhendrif.capstone.favorite.ui

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.ui.ViewModelFactory
import com.mhendrif.capstone.ui.base.BaseFragment
import com.mhendrif.capstone.common.util.SortOrder
import com.mhendrif.capstone.core.di.CoreComponent
import com.mhendrif.capstone.core.di.DaggerCoreComponent
import com.mhendrif.capstone.core.util.ItemListener
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.favorite.R
import com.mhendrif.capstone.favorite.databinding.FragmentFavoriteMovieBinding
import com.mhendrif.capstone.favorite.di.DaggerFavoriteComponent
import javax.inject.Inject

class FavoriteMovieFragment : BaseFragment<FragmentFavoriteMovieBinding>(R.layout.fragment_favorite_movie),
    ItemListener<Movie> {

    companion object {
        private const val ARG_SECTION_NUMBER = Constants.ARG_FAVORITE_MOVIE
        fun newInstance(index: Int) = FavoriteMovieFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SECTION_NUMBER, index) }
        }
    }

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
    private lateinit var adapter: FavoriteMovieAdapter

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            adapter = FavoriteMovieAdapter().apply {
                onItemListener = this@FavoriteMovieFragment
                binding.rvMovie.setHasFixedSize(true)
                binding.rvMovie.adapter = this
            }
            favoriteViewModel.movies.observe(viewLifecycleOwner, { handleStat(it) })
            favoriteViewModel.getFavoriteMovies()
        }
    }

    private fun handleStat(resource: List<Movie>) {
        with(binding) {
            if (resource.isNotEmpty()) {
                isLoading = false
                viewDataEmpty.isEmptyData = false
                rvMovie.visibility = View.VISIBLE
                adapter.submitList(resource)
            } else {
                isLoading = false
                viewDataEmpty.isEmptyData = true
                rvMovie.visibility = View.GONE
            }
        }
    }

    private fun navigateToDetail(model: Movie) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteFragmentToDetailMovieFragment(model)
        )
    }

    override fun onItemClick(model: Movie) {
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
                favoriteViewModel.sorting(SortOrder.BY_NAME);true
            }
            R.id.by_release -> {
                item.isChecked = true
                favoriteViewModel.sorting(SortOrder.BY_DATE);true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
