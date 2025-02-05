package com.android.study.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R
import com.android.study.ui.adapter.ListRecomendedVideoAdapter
import com.android.study.ui.models.VideoItem

class VideoListFragment: Fragment() {
    private lateinit var videoAdapter: ListRecomendedVideoAdapter
    private lateinit var recyclerViewplayerView: RecyclerView
    private val videoList = listOf(
        VideoItem("1","https://url_do_video_1.mp4", R.drawable.img),
        VideoItem("2","https://url_do_video_2.mp4",  R.drawable.img),
        VideoItem("3","https://url_do_video_3.mp4",  R.drawable.img)
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_recommended_video, container, false)
        recyclerViewplayerView = view.findViewById(R.id.recyclerViewListVideos)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        videoAdapter = ListRecomendedVideoAdapter(videoList) { videoUrl -> runVideo(videoUrl) }
        recyclerViewplayerView.layoutManager = LinearLayoutManager(context)
        recyclerViewplayerView.adapter = videoAdapter
    }

    private fun runVideo(url: String) { print(url) }
}