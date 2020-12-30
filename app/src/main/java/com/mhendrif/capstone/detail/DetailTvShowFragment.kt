package com.mhendrif.capstone.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mhendrif.capstone.databinding.FragmentDetailTvShowBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailTvShowFragment : Fragment() {

    private val viewModel: DetailTvShowViewModel by viewModel()
    private var _binding: FragmentDetailTvShowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}