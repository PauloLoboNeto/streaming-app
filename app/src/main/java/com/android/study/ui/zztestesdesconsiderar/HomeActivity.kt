package com.android.study.ui.zztestesdesconsiderar

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.android.study.databinding.HomeActivityBinding
import com.android.study.ui.activities.VideoActivity

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: HomeActivityBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiData.observe(this, Observer { data ->
            binding.textView.text = data
        })

        binding.button.setOnClickListener {
            viewModel.loadData()
        }

        binding.buttonReprodutor.setOnClickListener {
            val intent = Intent(this, VideoActivity::class.java)
            startActivity(intent)
        }
    }
}
