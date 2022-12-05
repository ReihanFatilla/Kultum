package com.reift.kultum.presentation.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.reift.core.domain.model.Kultum
import com.reift.kultum.`interface`.YoutubePlayCallBack
import com.reift.kultum.databinding.ActivityPostBinding
import com.reift.kultum.utils.UrlFormatter
import org.koin.android.viewmodel.ext.android.viewModel

class PostActivity : AppCompatActivity() {

    private var _binding: ActivityPostBinding? = null
    private val binding get() = _binding as ActivityPostBinding

    private val viewModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.apply {
            btnSearchVid.setOnClickListener {
                urlInputValidation()
            }
            btnPost.setOnClickListener {
                viewModel.isVideoFound.observe(this@PostActivity){
                    if(it){
                        postKultum()
                    } else {
                        Toast.makeText(this@PostActivity, "Please add Your Your Video First", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            btnBack.setOnClickListener{
                finish()
            }

        }
    }

    private fun postKultum() {
        binding.apply {
            val urlKultum = UrlFormatter.format(edtLink.text.toString()) ?: "nFr1Jj1KxVk"
            val caption = edtCaption.text.toString()
            viewModel.postKultum(caption, urlKultum)
        }
    }

    private fun urlInputValidation() {
        binding.apply {
            if (edtLink.text.isNullOrEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Please fill the Video Url",
                    Toast.LENGTH_SHORT
                ).show()
                edtLink.requestFocus()
            } else {
                searchYoutubeVideo(edtLink.text.toString())
            }
        }
    }

    private fun searchYoutubeVideo(url: String) {
        binding.apply {


            val urlKey = UrlFormatter.format(url)

            val listener = object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    if (urlKey != null) {
                        youTubePlayer.loadVideo(urlKey, 0f)
                    }

                    Toast.makeText(applicationContext, "if the video doesn't show, please try a new link", Toast.LENGTH_SHORT).show()

                }
            }

            val options = IFramePlayerOptions.Builder()
                .controls(0)
                .ivLoadPolicy(3)
                .ccLoadPolicy(1)
                .build()
            ytPlayer.initialize(listener, options)

            viewModel.isVideoFound.value = true

        }
    }
}