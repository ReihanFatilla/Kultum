package com.reift.kultum.presentation.home.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import androidx.fragment.app.Fragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBarListener
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.`interface`.YoutubePlayCallBack
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentKultumBinding
import com.reift.kultum.presentation.comment.CommentFragment
import com.reift.kultum.presentation.home.HomeViewModel
import com.reift.kultum.utils.Animator
import com.reift.kultum.utils.ShareMessage
import org.koin.android.viewmodel.ext.android.viewModel


class KultumFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentKultumBinding? = null
    private val binding get() = _binding as FragmentKultumBinding

    private lateinit var kultum: Kultum

    private var isPlaying = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentKultumBinding.inflate(layoutInflater)

        kultum = arguments?.getParcelable(KultumViewPagerAdapter.BUNDLE_KULTUM)!!

        setUpShortsVideo()

        return binding.root
    }

    private fun onCommentClicked() {
        binding.btnComment.setOnClickListener {
            val commentFragment = CommentFragment()
            val bundle = Bundle()
            Animator.bouncyAnimation(it)
            bundle.putString(Constant.BUNDLE_URL_KULTUM, kultum.urlKey)
            commentFragment.arguments = bundle

            commentFragment.show(requireActivity().supportFragmentManager, null)
        }
    }


    private fun initObserver() {
        viewModel.getKultumDetail(kultum.urlKey).observe(viewLifecycleOwner) {
            setUpDetail(it)
            setUpHelfpulButton(it)
        }
    }

    private fun setUpDetail(kultum: Kultum) {
        binding.apply {
            with(kultum) {
                tvCaption.text = caption
                tvCreator.text = "@$creator"
                tvCommentAmount.text = comments.size.toString()
                tvHelfpulAmount.text = helpful.size.toString()
            }
        }
    }

    private fun setUpHelfpulButton(kultum: Kultum) {
        binding.apply {
            viewModel.isKultumHelpfuled(kultum.urlKey).observe(viewLifecycleOwner) { isHelpfuled ->
                btnHelpful.isChecked = isHelpfuled
                btnHelpful.setOnClickListener {
                    Animator.jumpAnimation(btnHelpful)
                    if (isHelpfuled) {
                        viewModel.removeKultum(kultum.urlKey)
                    } else {
                        viewModel.addHelpfulKultum(kultum.urlKey)
                    }
                }

            }
        }
    }

    private fun setUpShortsVideo() {
        binding.apply {
            var youtubePlayCallBack: YoutubePlayCallBack? = null

            lifecycle.addObserver(binding.ytPlayer)

            ytPlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(kultum.urlKey, 0f)

                    playPauseArea.setOnClickListener {
                        isPlaying = if (isPlaying) {
                            youTubePlayer.pause()
                            icPlay.animate().scaleX(1f).scaleY(1f).alpha(1f).setDuration(300)
                            false
                        } else {
                            icPlay.animate().scaleX(0f).scaleY(0f).alpha(0f).setDuration(300)
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

                    val tracker = YouTubePlayerTracker()
                    youTubePlayer.addListener(tracker)

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

    override fun onResume() {
        super.onResume()
        initObserver()
        onCommentClicked()
        onShareClicked()
    }

    private fun onShareClicked() {
        binding.btnShare.setOnClickListener {
            Animator.bouncyAnimation(it)
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, ShareMessage.generateMessage(kultum))
                type = "text/plain"
            }

            val shareIntent =
                Intent.createChooser(sendIntent, getString(R.string.txt_share_msg))
            startActivity(shareIntent)
        }
    }

}