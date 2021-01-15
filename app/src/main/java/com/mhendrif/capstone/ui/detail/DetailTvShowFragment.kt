package com.mhendrif.capstone.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.R
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.core.util.DialogMessage
import com.mhendrif.capstone.databinding.FragmentDetailTvShowBinding
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.TvShow
import com.mhendrif.capstone.ui.ViewModelFactory
import com.mhendrif.capstone.ui.base.BaseFragment
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.roundToInt


class DetailTvShowFragment : BaseFragment<FragmentDetailTvShowBinding>(R.layout.fragment_detail_tv_show) {

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val detailViewModel: DetailViewModel by viewModels { factory }
    private val args: DetailTvShowFragmentArgs by navArgs()
    private lateinit var tvShow: TvShow

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.tvShow.observe(viewLifecycleOwner, { handleStat(it) })
        detailViewModel.getDetailTvShow(args.tvShow.id)
        tvShow = args.tvShow
    }

    private fun handleStat(resource: Resource<TvShow>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> {
                    isLoading = true
                    isVisibleContent = false
                }
                is Resource.Success -> {
                    args = resource.data
                    isLoading = false
                    isVisibleContent = true
                    resource.data?.let {
                        setUpContent(it)
                        tvShow = it
                    }
                }
                is Resource.Error -> {
                    isLoading = false
                    isVisibleContent = true
                    Timber.e(resource.message)
                    activity?.toast(resource.message.toString())
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpContent(model: TvShow) {
        with(binding) {
            val genres = ArrayList<String>()
            for (genre in model.genres!!) {
                genres.add(genre.name)
            }
            tvGenre.text = genres.joinToString()
            pbScore.progress = (model.voteAverage * 10).roundToInt()

            tvReadMore.setOnClickListener {
                if (tvReadMore.text.toString() == "Read More") {
                    tvOverview.maxLines = Int.MAX_VALUE
                    tvOverview.ellipsize = null
                    tvReadMore.setText(R.string.read_less)
                } else {
                    tvOverview.maxLines = 4
                    tvOverview.ellipsize = TextUtils.TruncateAt.END
                    tvReadMore.setText(R.string.read_more)
                }
            }

            ivFavorite.setOnClickListener {
                DialogMessage.showDialog(requireContext(), model.title, model.isFavorite) {
                    detailViewModel.setFavoriteTvShow(model, !model.isFavorite)
                    activity?.toast("Success ${if (model.isFavorite) "delete" else "add"} ${model.title} ${if (model.isFavorite) "from" else "to"} favorite")
                }
            }
        }
    }

    private fun openLink(url: String?) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }.also {
            activity?.startActivity(it)
        }
    }

    private fun setUpBottomNavigation() {
        val bottomNav: BottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation_view)
        bottomNav.visibility = View.GONE
    }

    override fun onResume() {
        super.onResume()
        val actionBar: ActionBar? = (activity as MainActivity?)?.supportActionBar
        actionBar?.apply {
            title = getString(R.string.detail_tv_show)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        setUpBottomNavigation()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.detail_nav_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed();true
            }
            R.id.action_open_link -> {
                if (tvShow.homepage.isNullOrEmpty())
                    openLink(Constants.TMDB_TV_URL + tvShow.id)
                else
                    openLink(tvShow.homepage)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
