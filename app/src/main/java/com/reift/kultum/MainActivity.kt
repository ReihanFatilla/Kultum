package com.reift.kultum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.reift.kultum.databinding.ActivityMainBinding
import com.reift.kultum.utils.Transparent

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Transparent.statusbar(this)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.bottomNav.setupWithNavController(navController)
    }
}