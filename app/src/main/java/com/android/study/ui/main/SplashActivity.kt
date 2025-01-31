package com.android.study.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.android.study.ui.features.video.VideoActivity

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this@SplashActivity, VideoActivity::class.java)
        startActivity(intent)
        finish()
    }
}
