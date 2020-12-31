package com.mhendrif.capstone.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.ui.MovieAdapter
import com.mhendrif.capstone.core.ui.TvShowAdapter
import com.mhendrif.capstone.databinding.FragmentTvShowBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val tvShowViewModel: TvShowViewModel by viewModels()
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowAdapter = TvShowAdapter()
            tvShowAdapter.onItemClick = { selectData ->
                Timber.d(selectData.title)
            }

            tvShowViewModel.tvShow.observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when(tvShow) {
                        is Resource.Loading ->  binding.pbLoading.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbLoading.visibility = View.GONE
                            tvShowAdapter.setData(tvShow.data)
                        }
                        is Resource.Error -> {
                            binding.pbLoading.visibility = View.GONE
                            Timber.e(tvShow.message)
                            activity?.toast(tvShow.message.toString())
                        }
                    }
                } else {
                    activity?.toast("Data is null")
                }
            })

            with(binding.rvTvShow) {
                setHasFixedSize(true)
                adapter = tvShowAdapter
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