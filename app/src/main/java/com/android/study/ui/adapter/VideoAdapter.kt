package com.android.study.ui.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R

class VideoAdapter: RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.playervideo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        this.setupPlayer(holder.playerContainer.context, holder.playerContainer)
    }

    override fun getItemCount(): Int = 5

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerContainer: PlayerView = view.findViewById(R.id.videoPlayer1)
    }

    @OptIn(UnstableApi::class)
    private fun setupPlayer(context: Context, playerContainer: PlayerView){
        var exoPlayer = ExoPlayer.Builder(context).build()

        val videoUri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
        val mediaItem = MediaItem.fromUri(videoUri)
        exoPlayer.setMediaItem(mediaItem)

        playerContainer.player = exoPlayer

        playerContainer.setShowRewindButton(false)
        playerContainer.setShowFastForwardButton(false)
        playerContainer.setShowPreviousButton(false)
        playerContainer.setShowNextButton(false)

        exoPlayer.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Log.e("PlayerError", "Erro durante a reprodução: ${error.message}")
            }
        })

        exoPlayer.prepare()
    }
}