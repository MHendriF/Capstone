package com.mhendrif.capstone.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mhendrif.capstone.util.AutoClearedValue

abstract class BaseFragment<B : ViewDataBinding> constructor(
    @LayoutRes val layoutRes: Int
) : Fragment() {

    protected var binding by AutoClearedValue<B>()

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        DataBindingUtil.inflate<B>(inflater, layoutRes, container, false).also {
            binding = it
        }.root

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }
}
