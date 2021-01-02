package com.mhendrif.capstone.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.ui.MovieAdapter
import com.mhendrif.capstone.core.utils.SortOrder
import com.mhendrif.capstone.databinding.FragmentMovieBinding
import com.mhendrif.capstone.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteMovieFragment : Fragment() {

    companion object {
        private const val ARG_SECTION_NUMBER = "FAVORITE_MOVIE_FRAGMENT"

        fun newInstance(index: Int) = FavoriteMovieFragment().apply {
            arguments = Bundle().apply { putInt(ARG_SECTION_NUMBER, index) }
        }
    }

    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        if (activity != null) {
            favoriteViewModel.getFavoriteMovies()
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java).apply {
                    putExtra(
                        DetailActivity.DATA_EXTRA,
                        arrayListOf(R.id.fragmentDetailMovie, selectData.id)
                    )
                }
                activity?.startActivity(intent)
            }

            favoriteViewModel.movies.observe(viewLifecycleOwner, { movies ->
                if (movies != null && movies.isNotEmpty()) {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvMovie.visibility = View.VISIBLE
                    binding.viewDataEmpty.emptyAnimation.visibility = View.GONE
                    movieAdapter.setData(movies)
                } else {
                    binding.pbLoading.visibility = View.GONE
                    binding.rvMovie.visibility = View.GONE
                    binding.viewDataEmpty.emptyAnimation.visibility = View.VISIBLE
                }
            })

            with(binding.rvMovie) {
                setHasFixedSize(true)
                adapter = movieAdapter
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}