package com.android.study.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.study.R
import com.android.study.databinding.ListRecommendedVideoBinding
import com.android.study.ui.adapter.ListRecomendedVideoAdapter
import com.android.study.ui.models.VideoItem

class ListRecommendedVideoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var videoAdapter: ListRecomendedVideoAdapter
    private var binding: ListRecommendedVideoBinding

    private val videoList = listOf(
        VideoItem("1","https://url_do_video_1.mp4", R.drawable.img),
        VideoItem("2","https://url_do_video_2.mp4",  R.drawable.img),
        VideoItem("3","https://url_do_video_3.mp4",  R.drawable.img),
        VideoItem("4","https://url_do_video_3.mp4",  R.drawable.img),
        VideoItem("5","https://url_do_video_3.mp4",  R.drawable.img),
        VideoItem("6","https://url_do_video_3.mp4",  R.drawable.img),
        VideoItem("7","https://url_do_video_3.mp4",  R.drawable.img),
        VideoItem("8","https://url_do_video_3.mp4",  R.drawable.img)
    )

    init {
        binding = ListRecommendedVideoBinding.inflate(LayoutInflater.from(context),this, true)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        videoAdapter = ListRecomendedVideoAdapter(videoList) { videoUrl -> runVideo(videoUrl) }
        binding.recyclerViewListVideos.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListVideos.adapter = videoAdapter
    }

    private fun runVideo(url: String) { print(url) }
}