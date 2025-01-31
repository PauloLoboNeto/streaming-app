package com.android.study.ui.features.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R
import com.android.study.ui.adapter.ListCommentAdapter

class CommentsListFragment: Fragment() {
    private lateinit var commentsAdapter: ListCommentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_comment, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewListComments)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        commentsAdapter = ListCommentAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = commentsAdapter
    }
}