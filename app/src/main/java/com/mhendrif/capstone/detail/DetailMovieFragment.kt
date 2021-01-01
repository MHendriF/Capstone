package com.mhendrif.capstone.detail

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mhendrif.capstone.R
import com.mhendrif.capstone.core.binding.ImageBinding
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
    ): View? {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }
        Timber.d("Timber on create")

        val dataId = DetailMovieFragmentArgs.fromBundle(arguments as Bundle).movieId
        Timber.d("Timber dataId: %s", dataId)
        if(dataId != 0) detailViewModel.getDetailMovie(dataId)

        if (activity != null) {
            detailViewModel.movie.observe(viewLifecycleOwner, { movie ->
                if (movie != null) {
                    when(movie) {
                        is Resource.Loading ->  binding.pbLoading.visibility = View.VISIBLE
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
            ImageBinding.setImageURL(ivPoster, Constants.API_POSTER_PATH+model.posterPath)
            ImageBinding.setImageURL(ivBackground, Constants.API_BACKDROP_PATH+model.posterPath)
            tvTitle.text = model.title
            tvOverview.text = model.overview
            tvGenre.text = model.title
            tvReleaseDate.text = model.releaseDate
            tvScore.text = model.voteAverage.toString()
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
                    detailViewModel.setFavoriteMovie(model, model.isFavorite)
                    activity?.toast("Success ${if (model.isFavorite) "delete" else "add"} ${model.title} ${if (model.isFavorite) "from" else "to"} favorite")
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}