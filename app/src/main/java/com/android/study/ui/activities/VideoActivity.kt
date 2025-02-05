package com.android.study.ui.activities

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.OptIn
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.android.study.databinding.StreamVideoBinding
import com.android.study.ui.viewModels.VideoViewModel
import com.android.study.ui.views.ListRecommendedVideoView
import kotlin.math.max
import kotlin.math.min

class VideoActivity : AppCompatActivity() {
    //Implementar adaptativo
    //https://developer.android.com/codelabs/exoplayer-intro?hl=pt-br#4
    private var posicaoInicialToqueArrastoY = 0f
    private var posicaoInicialElementoY = 0f
    private var posicaoAtualDoToqueY = 0f

    private lateinit var binding: StreamVideoBinding
    private lateinit var player: ExoPlayer
    private lateinit var playerContainer: PlayerView
    private lateinit var listRecommendedVideosContainer: ListRecommendedVideoView
    private val viewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.loadVideo()
        binding = StreamVideoBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        playerContainer = binding.videoPlayer
        listRecommendedVideosContainer = binding.videosContainer

        playerContainer.setOnTouchListener(OnTouchEvent())
        this.setupPlayer()
    }

    override fun onStart() {
        this.player.play()
        super.onStart()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < 24) {
            this.player.release()
        }

        if (Build.VERSION.SDK_INT >= 24) {
            this.player.pause()
        }
    }

    override fun onDestroy() {
        this.player.release()
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        this.player.play()
    }

    private inner class OnTouchEvent(): View.OnTouchListener {
        override fun onTouch(p0: View?, e: MotionEvent): Boolean {
            when (e.action){
                MotionEvent.ACTION_DOWN -> tratarEventoToqueDoDedoNaTela(e.rawY)
                MotionEvent.ACTION_MOVE -> tratarMovimentoDeArrasto(e.rawY)
                MotionEvent.ACTION_UP -> tratarRemocaoDoDedo()
            }
            return true
        }
    }

    private fun tratarEventoToqueDoDedoNaTela(posicaoAtualDoToque: Float) {
        // Armazena a posição inicial do toque
        posicaoInicialToqueArrastoY = posicaoAtualDoToque
        posicaoInicialElementoY = playerContainer.translationY
    }

    private fun tratarMovimentoDeArrasto(posicaoAtualDoToque: Float){
        val tamanhoTela = (playerContainer.parent as View).height
        // Calcula o deslocamento do toque
        val deltaY = posicaoAtualDoToque - posicaoInicialToqueArrastoY
        // Calcula a nova posição vertical
        val novaPosicaoElementoY = posicaoInicialElementoY + deltaY
        // Garante que o vídeo não ultrapasse o rodapé (altura da tela - altura do player)
        val progress = playerContainer.translationY / tamanhoTela

        if (deltaY > 0 && (posicaoAtualDoToqueY - posicaoAtualDoToque <= 0)) {
            val layoutParams = playerContainer.layoutParams
            layoutParams.height = max(350,  1 - progress.toInt())
            playerContainer.requestLayout()
        } else {
            val layoutParams = playerContainer.layoutParams
            layoutParams.height = max(680, 1 - progress.toInt())
            playerContainer.requestLayout()
        }

        val posicaoMaximaPermitidaY = tamanhoTela - playerContainer.height.toFloat()
        playerContainer.translationY = min(max(0f, novaPosicaoElementoY), posicaoMaximaPermitidaY)

        //movimentar o scroll junto com o vídeo.
        listRecommendedVideosContainer.translationY = playerContainer.translationY
        posicaoAtualDoToqueY = posicaoAtualDoToque
    }

    @OptIn(UnstableApi::class)
    private fun tratarRemocaoDoDedo() {
        val screenHeight = (playerContainer.parent as View).height
        val currentY = playerContainer.translationY
        if (currentY > screenHeight / 2) {

            // Move o player para o rodapé
            val layoutParams = playerContainer.layoutParams
            layoutParams.height = 350
            playerContainer.requestLayout().also {
                playerContainer.animate().translationY(screenHeight - playerContainer.height.toFloat()).start()
                listRecommendedVideosContainer.animate().translationY(screenHeight - 350f).start()
            }

        } else {
            val layoutParams = playerContainer.layoutParams
            layoutParams.height = 680
            playerContainer.requestLayout().also {
                playerContainer.animate().translationY(0f).start()
                listRecommendedVideosContainer.animate().translationY(0f).start()
            }
        }
        playerContainer.showController()
    }

    @OptIn(UnstableApi::class)
    private fun setupPlayer(){
        player = ExoPlayer.Builder(this)
            .build()

        playerContainer.player = player

        playerContainer.setShowRewindButton(false)
        playerContainer.setShowFastForwardButton(false)
        playerContainer.setShowPreviousButton(false)
        playerContainer.setShowNextButton(false)

        viewModel.uiGetVideoLiveData.value?.let { player.setMediaItem(it) }

        player.addListener(object : Player.Listener {
            override fun onPlayerError(error: PlaybackException) {
                Log.e("PlayerError", "Erro durante a reprodução: ${error.message}")
            }
        })

//        player.prepare()
        // Começar a reprodução automática
//        player.playWhenReady = true
    }
}