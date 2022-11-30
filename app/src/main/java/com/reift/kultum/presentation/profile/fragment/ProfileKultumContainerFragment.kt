package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import android.util.Log
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
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.`interface`.YoutubePlayCallBack
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.databinding.FragmentKultumBinding
import com.reift.kultum.databinding.FragmentProfileKultumContainerBinding
import com.reift.kultum.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileKultumContainerFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentProfileKultumContainerBinding? = null
    private val binding get() = _binding as FragmentProfileKultumContainerBinding

    private lateinit var kultum: Kultum

    private var isPlaying = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileKultumContainerBinding.inflate(layoutInflater)

        kultum = arguments?.getParcelable(KultumViewPagerAdapter.BUNDLE_KULTUM)!!

        viewLifecycleOwner.lifecycle.addObserver(binding.ytPlayer)
        setUpShortsVideo()
        setUpHelfpulButton()
        initObserver()

        return binding.root
    }

    private fun initObserver() {
        viewModel.getKultumDetail(kultum.urlKey).observe(viewLifecycleOwner){
            setUpDetail(it)
        }
    }

    private fun setUpDetail(kultum: Kultum) {
        binding.apply {
            with(kultum) {
                tvCaption.text = caption
                tvCreator.text = creator
                tvCommentAmount.text = comments.size.toString()
                tvHelfpulAmount.text = helpful.size.toString()
                tvShareAmount.text = share.toString()
            }
        }
    }

    private fun setUpHelfpulButton() {
        binding.apply {
            viewModel.isKultumHelpfuled(kultum.urlKey).observe(viewLifecycleOwner) {

                if (it) {
                    btnHelpful.isChecked = true
                    btnHelpful.setOnClickListener {
                        viewModel.removeKultum(kultum.urlKey)
                    }
                } else {
                    btnHelpful.isChecked = false
                    btnHelpful.setOnClickListener {
                        viewModel.addHelpfulKultum(kultum.urlKey)
                    }
                }
            }
        }
    }

    private fun setUpShortsVideo() {
        binding.apply {

            var youtubePlayCallBack: YoutubePlayCallBack? = null

            ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(kultum.urlKey, 0f)

                    playPauseArea.setOnClickListener {
                        isPlaying = if (isPlaying) {
                            youTubePlayer.pause()
                            false
                        } else {
                            youTubePlayer.play()
                            true
                        }
                    }

                    youtubePlayCallBack = object : YoutubePlayCallBack {
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
                    if (state == PlayerConstants.PlayerState.ENDED) {
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