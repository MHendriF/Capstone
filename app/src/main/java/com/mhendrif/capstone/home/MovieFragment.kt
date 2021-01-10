package com.mhendrif.capstone.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.base.BaseFragment
import com.mhendrif.capstone.core.utils.ItemListener
import com.mhendrif.capstone.ui.MovieAdapter
import com.mhendrif.capstone.databinding.FragmentMovieBinding
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.Movie
import timber.log.Timber
import javax.inject.Inject

class MovieFragment : BaseFragment<FragmentMovieBinding>(R.layout.fragment_movie),
    ItemListener<Movie> {

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val movieViewModel: MovieViewModel by viewModels { factory }
    private lateinit var adapter: MovieAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            adapter = MovieAdapter().apply {
                onItemListener = this@MovieFragment
                binding.rvMovie.setHasFixedSize(true)
                binding.rvMovie.adapter = this
            }
            movieViewModel.movie.observe(viewLifecycleOwner, { handleStat(it) })
        }
    }

    private fun handleStat(resource: Resource<List<Movie>>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> {
                    isLoading = true
                    viewNetworkError.isNetworkError = false
                    rvMovie.visibility = View.GONE
                }
                is Resource.Success -> {
                    isLoading = false
                    viewNetworkError.isNetworkError = false
                    rvMovie.visibility = View.VISIBLE
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    isLoading = false
                    viewNetworkError.isNetworkError = false
                    rvMovie.visibility = View.GONE
                    Timber.e(resource.message)
                    activity?.toast(resource.message.toString())
                }
            }
        }
    }

    private fun navigateToDetail(movie: Movie) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToDetailMovieFragment(movie)
        )
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(model: Movie) {
        navigateToDetail(model)
    }

    override fun onResume() {
        super.onResume()
        val actionBar: ActionBar? = (activity as MainActivity?)?.supportActionBar
        actionBar?.apply {
            title = getString(R.string.movie)
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(true)
        }
    }
}