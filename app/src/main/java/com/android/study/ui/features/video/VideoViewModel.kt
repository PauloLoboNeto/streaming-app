package com.android.study.ui.features.video

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.media3.common.MediaItem

class VideoViewModel : ViewModel() {
    private val _uiGetVideoLiveData = MutableLiveData<MediaItem>()
    val uiGetVideoLiveData: LiveData<MediaItem> = _uiGetVideoLiveData

    fun loadVideo() {
        val videoUri = Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
        this._uiGetVideoLiveData.value = MediaItem.fromUri(videoUri)

    }
}