package com.mhendrif.capstone.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> constructor(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected open lateinit var binding: B

    private fun bindView() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
    }
}
