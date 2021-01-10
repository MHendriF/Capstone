package com.mhendrif.capstone.detail

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
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mhendrif.capstone.MainActivity
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.base.BaseFragment
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.core.utils.DialogMessage
import com.mhendrif.capstone.databinding.FragmentDetailMovieBinding
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.Movie
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.roundToInt

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding>(R.layout.fragment_detail_movie) {

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val detailViewModel: DetailViewModel by viewModels { factory }
    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var movie: Movie

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailViewModel.movie.observe(viewLifecycleOwner, { handleStat(it) })
        detailViewModel.getDetailMovie(args.movie.id)
        movie = args.movie
    }

    private fun handleStat(resource: Resource<Movie>) {
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
                        movie = it
                    }
                }
                is Resource.Error -> {
                    isLoading = false
                    isVisibleContent = false
                    Timber.e(resource.message)
                    activity?.toast(resource.message.toString())
                }
            }
        }
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun setUpContent(model: Movie) {
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

            var isFavorite = model.isFavorite
            setStatusFavorite(isFavorite)
            ivFavorite.setOnClickListener {
                DialogMessage.showDialog(requireContext(), model.title, isFavorite) {
                    isFavorite = !isFavorite
                    detailViewModel.setFavoriteMovie(model, isFavorite)
                    activity?.toast("Success ${if (!isFavorite) "delete" else "add"} ${model.title} ${if (!isFavorite) "from" else "to"} favorite")
                    setStatusFavorite(isFavorite)
                }
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (!statusFavorite) {
            binding.ivFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_favorite
                    )
            )
        } else {
            binding.ivFavorite.setImageDrawable(
                    ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_delete
                    )
            )
        }
    }

    private fun openLink(url: String?) {
        Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(url)
        }.also {
            activity?.startActivity(it)
        }
    }

    override fun onResume() {
        super.onResume()
        val actionBar: ActionBar? = (activity as MainActivity?)?.supportActionBar
        actionBar?.apply {
            title = getString(R.string.detail_movie)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
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
                if (movie.homepage.isNullOrEmpty())
                    openLink(Constants.TMDB_MOVIE_URL+movie.id)
                else
                    openLink(movie.homepage)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}