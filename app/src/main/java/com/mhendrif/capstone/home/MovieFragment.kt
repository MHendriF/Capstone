package com.mhendrif.capstone.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.ui.MovieAdapter
import com.mhendrif.capstone.databinding.FragmentMovieBinding
import com.mhendrif.capstone.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val movieViewModel: MovieViewModel by viewModels()
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

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { selectData ->
                val intent = Intent(activity, DetailActivity::class.java).apply {
                    putExtra(
                        DetailActivity.DATA_EXTRA,
                        arrayListOf(R.id.fragmentDetailMovie, selectData.id)
                    )
                }
                Timber.d("Timber des: %s - id: %s", R.id.fragmentDetailMovie, selectData.id)
                activity?.startActivity(intent)
            }

            movieViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.pbLoading.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbLoading.visibility = View.GONE
                            movieAdapter.setData(movie.data)
                        }
                        is Resource.Error -> {
                            binding.pbLoading.visibility = View.GONE
                            Timber.e(movie.message)
                            activity?.toast(movie.message.toString())
                        }
                    }
                } else {
                    binding.pbLoading.visibility = View.GONE
                    activity?.toast("Data is null")
                }
            })

            with(binding.rvMovie) {
                setHasFixedSize(true)
                adapter = movieAdapter
            }
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