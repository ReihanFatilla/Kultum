package com.reift.kultum.presentation.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBarListener
import com.reift.kultum.R
import com.reift.kultum.`interface`.YoutubePlayCallBack
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.databinding.FragmentKultumBinding

class KultumFragment : Fragment() {

    private var _binding: FragmentKultumBinding? = null
    private val binding get() = _binding as FragmentKultumBinding

    private lateinit var url: String

    private var isPlaying = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKultumBinding.inflate(layoutInflater)

        url = arguments?.getString(KultumViewPagerAdapter.BUNDlE_VIDEO_URL) ?: "nFr1Jj1KxVk"

        setUpShortsVideo()
        lifecycle.addObserver(binding.ytPlayer)

        return binding.root
    }

    private fun setUpShortsVideo() {
        binding.apply {

            var youtubePlayCallBack: YoutubePlayCallBack? = null

            ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(url, 0f)

                    playPauseArea.setOnClickListener {
                        if (isPlaying) {
                            youTubePlayer.pause()
                            isPlaying = false
                        } else {
                            youTubePlayer.play()
                            isPlaying = true
                        }
                    }

                    youtubePlayCallBack = object : YoutubePlayCallBack{
                        override fun onCalled(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.seekTo(0f)
                        }
                    }

                    youTubePlayer.addListener(ytSeekbar)

                    ytSeekbar.youtubePlayerSeekBarListener = object :
                        YouTubePlayerSeekBarListener {
                        override fun seekTo(time: Float) {
                            youTubePlayer.seekTo(time)
                        }
                    }
                }
            })

            val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
                override fun onStateChange(
                    youTubePlayer: YouTubePlayer,
                    state: PlayerConstants.PlayerState
                ) {
                    super.onStateChange(youTubePlayer, state)
                    if(state == PlayerConstants.PlayerState.ENDED){
                        youtubePlayCallBack?.onCalled(youTubePlayer)
                    }
                }
            }

            val options = IFramePlayerOptions.Builder()
                .controls(0)
                .ivLoadPolicy(3)
                .ccLoadPolicy(1)
                .build()
            ytPlayer.enableAutomaticInitialization = false
            ytPlayer.initialize(listener, options)
        }
    }

}