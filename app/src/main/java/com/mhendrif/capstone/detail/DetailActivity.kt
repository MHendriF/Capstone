package com.mhendrif.capstone.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.mhendrif.capstone.R
import com.mhendrif.capstone.ViewModelFactory
import com.mhendrif.capstone.base.BaseActivity
import com.mhendrif.capstone.databinding.ActivityDetailBinding
import timber.log.Timber
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    companion object {
        const val DATA_DESTINATION = 0
        const val DATA_ID = 1
        const val DATA_EXTRA = "DATA_EXTRA"
        const val DATA_EXTRA_ID = "DATA_EXTRA_ID"
    }

    @Inject
    internal lateinit var factory: ViewModelFactory
    private val detailViewModel by viewModels<DetailViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        intent.getIntegerArrayListExtra(DATA_EXTRA)?.apply {
//            detailViewModel.init(get(DATA_DESTINATION), get(DATA_ID))
//            Timber.d("Timber des: %s - id: %s", get(DATA_DESTINATION), get(DATA_DESTINATION))
//        }
//
//        val navController =
//            Navigation.findNavController(this@DetailActivity, R.id.nav_host_detail_fragment)
//        savedInstanceState?.let {
//            navController.restoreState(it)
//        } ?: run {
//            navController.navInflater.inflate(R.navigation.nav_graph_detail).run {
//                startDestination = detailViewModel.getExtra(DATA_DESTINATION)
//                navController.setGraph(
//                    this,
//                    bundleOf(DATA_EXTRA_ID to detailViewModel.getExtra(DATA_ID))
//                )
//            }
//        }
    }
}