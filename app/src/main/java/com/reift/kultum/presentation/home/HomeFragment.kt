package com.reift.kultum.presentation.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.databinding.ActivityMainBinding
import com.reift.kultum.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        setUpKultumViewPager()

        return binding.root
    }

    private fun setUpKultumViewPager() {
        val dummyKultum = getDummyKultum()
        binding.vpKultum.adapter = activity?.let { KultumViewPagerAdapter(it.supportFragmentManager, dummyKultum, dummyKultum.size) }
    }

    private fun getDummyKultum(): List<String> {
        return listOf(
            "6waRY3TmrDA",
            "XDD3XWnCzxg",
            "nFr1Jj1KxVk",
            "JQFCyrMb5FI",
            "mjc6YEWD4x0",
            "MaBT61Hic1U",
        )
    }

}