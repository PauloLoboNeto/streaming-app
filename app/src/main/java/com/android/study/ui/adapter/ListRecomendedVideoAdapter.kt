package com.android.study.ui.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.recyclerview.widget.RecyclerView
import com.android.study.R
import com.android.study.ui.models.VideoItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.CropCircleWithBorderTransformation
import jp.wasabeef.glide.transformations.CropTransformation

//extende de RecyclerView.Adapter
class ListRecomendedVideoAdapter(
    private val videoList: List<VideoItem>,
    private val onVideoClick: (url: String) -> Unit ): RecyclerView.Adapter<ListRecomendedVideoAdapter.ViewHolder>() {

        // Declara qual(quais) views serão utilizadas para receber informações
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val thumbnailImage: ImageView = view.findViewById(R.id.thumbnailImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.thumbnail_image_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val videoItem = videoList[position]

        Glide.with(holder.thumbnailImage.context)
            .load(videoItem.thumbnailUrl)
            .into(holder.thumbnailImage)

        holder.thumbnailImage.setOnClickListener {
            onVideoClick(videoItem.videoUrl)
        }
    }

    override fun getItemCount(): Int = videoList.size

//    @OptIn(UnstableApi::class)
//    private fun setupPlayer(context: Context, playerContainer: PlayerView){
//        var exoPlayer = ExoPlayer.Builder(context).build()
//
//        val videoUri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
//        val mediaItem = MediaItem.fromUri(videoUri)
//        exoPlayer.setMediaItem(mediaItem)
//
//        playerContainer.player = exoPlayer
//
//        playerContainer.setShowRewindButton(false)
//        playerContainer.setShowFastForwardButton(false)
//        playerContainer.setShowPreviousButton(false)
//        playerContainer.setShowNextButton(false)
//
//        exoPlayer.addListener(object : Player.Listener {
//            override fun onPlayerError(error: PlaybackException) {
//                Log.e("PlayerError", "Erro durante a reprodução: ${error.message}")
//            }
//        })
//
//        exoPlayer.prepare()
//    }
}