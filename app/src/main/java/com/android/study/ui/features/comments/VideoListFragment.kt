package com.android.study.ui.features.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R
import com.android.study.ui.adapter.ListRecomendedVideoAdapter

class VideoListFragment: Fragment() {
    private lateinit var videoAdapter: ListRecomendedVideoAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_recommended_video, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewListVideos)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        videoAdapter = ListRecomendedVideoAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = videoAdapter
    }
}