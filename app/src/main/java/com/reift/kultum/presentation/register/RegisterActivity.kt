package com.reift.kultum.presentation.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reift.kultum.R
import com.reift.kultum.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private var _binding : ActivityRegisterBinding? = null
    private val binding get() = _binding as ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}