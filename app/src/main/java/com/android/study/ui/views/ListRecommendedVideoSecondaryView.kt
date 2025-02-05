package com.android.study.ui.views

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R
import com.android.study.databinding.ListCommentBinding
import com.android.study.ui.adapter.ListCommentAdapter

class ListRecommendedVideoSecondaryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var commentsAdapter: ListCommentAdapter
    private var binding: ListCommentBinding

    init {
        binding = ListCommentBinding.inflate(LayoutInflater.from(context),this, true)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        commentsAdapter = ListCommentAdapter()
        binding.recyclerViewListComments.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewListComments.adapter = commentsAdapter
    }
}