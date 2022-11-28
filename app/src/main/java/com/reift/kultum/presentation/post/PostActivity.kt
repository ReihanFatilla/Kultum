package com.reift.kultum.presentation.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reift.kultum.databinding.ActivityPostBinding
import org.koin.android.viewmodel.ext.android.viewModel

class PostActivity : AppCompatActivity() {

    private var _binding: ActivityPostBinding? = null
    private val binding get() = _binding as ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
    }

    private fun setUpView() {
        binding.apply {

        }
    }
}