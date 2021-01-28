package com.mhendrif.capstone

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.mhendrif.capstone.common.util.Constants
import com.mhendrif.capstone.databinding.ActivitySplashBinding
import com.mhendrif.capstone.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            Constants.DELAY
        )
    }
}
