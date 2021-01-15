package com.mhendrif.capstone.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mhendrif.capstone.MyApplication
import com.mhendrif.capstone.di.AppComponent

abstract class BaseActivity<B : ViewDataBinding> constructor(
    @LayoutRes val layoutRes: Int
) : AppCompatActivity() {

    protected open lateinit var binding: B
    protected open lateinit var appComponent: AppComponent

    private fun bindView() {
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.lifecycleOwner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent = (application as MyApplication).appComponent
        super.onCreate(savedInstanceState)
        bindView()
    }
}
