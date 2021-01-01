package com.mhendrif.capstone.detail

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.utils.ImageBinding
import com.mhendrif.capstone.core.data.Resource
import com.mhendrif.capstone.core.domain.model.Movie
import com.mhendrif.capstone.core.utils.Constants
import com.mhendrif.capstone.core.utils.DialogMessage
import com.mhendrif.capstone.databinding.FragmentDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private val detailViewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailMovieBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }

        val dataId = arguments?.get(DetailActivity.DATA_EXTRA_ID)
        if (dataId != 0 && dataId != null) detailViewModel.getDetailMovie(dataId as Int)

        if (activity != null) {
            detailViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when (movie) {
                        is Resource.Loading -> binding.pbLoading.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pbLoading.visibility = View.GONE
                            visibleContent()
                            movie.data?.let { setUpContent(it) }
                        }
                        is Resource.Error -> {
                            binding.pbLoading.visibility = View.GONE
                            Timber.e(movie.message)
                            activity?.toast(movie.message.toString())
                        }
                    }
                } else {
                    activity?.toast("Data is null")
                }
            })
        }
        Timber.d("Timber end on create")
    }

    private fun Context.toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun visibleContent() {
        with(binding) {
            ivBackground.visibility = View.VISIBLE
            ivPoster.visibility = View.VISIBLE
            tvTitle.visibility = View.VISIBLE
            tvOverview.visibility = View.VISIBLE
            tvGenre.visibility = View.VISIBLE
            tvReleaseDate.visibility = View.VISIBLE
            tvScore.visibility = View.VISIBLE
            tvReadMore.visibility = View.VISIBLE
            ivFavorite.visibility = View.VISIBLE
        }
    }

    private fun setUpContent(model: Movie) {
        with(binding) {
            ImageBinding.setImageURL(ivPoster, Constants.API_POSTER_PATH + model.posterPath)
            ImageBinding.setImageURL(ivBackground, Constants.API_BACKDROP_PATH + model.posterPath)
            val genres = ArrayList<String>()
            for (genre in model.genres!!) {
                genres.add(genre.name)
            }

            tvTitle.text = model.title
            tvOverview.text = model.overview
            tvReleaseDate.text = model.releaseDate
            tvScore.text = model.voteAverage.toString()
            tvGenre.text = genres.joinToString()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}