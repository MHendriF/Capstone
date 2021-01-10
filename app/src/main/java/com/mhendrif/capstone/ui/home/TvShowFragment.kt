package com.mhendrif.capstone.ui.home

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ui.ViewModelFactory
import com.mhendrif.capstone.ui.base.BaseFragment
import com.mhendrif.capstone.core.util.ItemListener
import com.mhendrif.capstone.ui.TvShowAdapter
import com.mhendrif.capstone.databinding.FragmentTvShowBinding
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.TvShow
import timber.log.Timber
import javax.inject.Inject

class TvShowFragment : BaseFragment<FragmentTvShowBinding>(R.layout.fragment_tv_show),
    ItemListener<TvShow> {

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val tvShowViewModel: TvShowViewModel by viewModels { factory }
    private lateinit var adapter: TvShowAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            adapter = TvShowAdapter().apply {
                onItemListener = this@TvShowFragment
                binding.rvTvShow.setHasFixedSize(true)
                binding.rvTvShow.adapter = this
            }
            tvShowViewModel.tvShow.observe(viewLifecycleOwner, { handleStat(it) })
        }
    }

    private fun handleStat(resource: Resource<List<TvShow>>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> {
                    isLoading = true
                    viewNetworkError.isNetworkError = false
                    rvTvShow.visibility = View.GONE
                }
                is Resource.Success -> {
                    isLoading = false
                    viewNetworkError.isNetworkError = false
                    rvTvShow.visibility = View.VISIBLE
                    adapter.submitList(resource.data)
                }
                is Resource.Error -> {
                    isLoading = false
                    viewNetworkError.isNetworkError = false
                    rvTvShow.visibility = View.GONE
                    Timber.e(resource.message)
                    activity?.toast(resource.message.toString())
                }
            }
        }
    }

    private fun navigateToDetail(model: TvShow) {
        findNavController().navigate(
            TvShowFragmentDirections.actionTvShowFragmentToDetailTvShowFragment(model)
        )
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(model: TvShow) {
        navigateToDetail(model)
    }

    private fun setUpBottomNavigation() {
        val bottomNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNav.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        val actionBar: ActionBar? = (activity as MainActivity?)?.supportActionBar
        actionBar?.apply {
            title = getString(R.string.tv_show)
            setDisplayHomeAsUpEnabled(false)
            setHomeButtonEnabled(true)
        }
        setUpBottomNavigation()
    }
}