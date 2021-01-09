package com.mhendrif.capstone.detail

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.base.BaseFragment
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.core.utils.ImageBinding
import com.mhendrif.capstone.core.utils.DialogMessage
import com.mhendrif.capstone.databinding.FragmentDetailTvShowBinding
import com.mhendrif.capstone.domain.Resource
import com.mhendrif.capstone.domain.model.Movie
import com.mhendrif.capstone.domain.model.TvShow
import timber.log.Timber
import javax.inject.Inject

class DetailTvShowFragment : BaseFragment<FragmentDetailTvShowBinding>(R.layout.fragment_detail_tv_show) {

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val detailViewModel: DetailViewModel by viewModels { factory }
    private val args: DetailTvShowFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener { activity?.onBackPressed() }

        Timber.d("dataId : %s", args.tvShow.id)
        detailViewModel.tvShow.observe(viewLifecycleOwner, { handleStat(it) })
        detailViewModel.getDetailTvShow(args.tvShow.id)
    }

    private fun handleStat(resource: Resource<TvShow>) {
        with(binding) {
            when (resource) {
                is Resource.Loading -> isLoading = true
                is Resource.Success -> {
                    isLoading = false
                    args = resource.data
                    visibleContent()
                    resource.data?.let { setUpContent(it) }
                }
                is Resource.Error -> {
                    isLoading = false
                    Timber.e(resource.message)
                    activity?.toast(resource.message.toString())
                }
            }
        }
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

    private fun setUpContent(model: TvShow) {
        with(binding) {
            val genres = ArrayList<String>()
            for (genre in model.genres!!) {
                genres.add(genre.name)
            }
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
                    detailViewModel.setFavoriteTvShow(model, isFavorite)
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
}