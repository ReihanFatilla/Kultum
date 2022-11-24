package com.reift.kultum.presentation.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reift.kultum.R
import com.reift.kultum.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var _binding : ActivityLoginBinding? = null
    private val binding get() = _binding as ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}