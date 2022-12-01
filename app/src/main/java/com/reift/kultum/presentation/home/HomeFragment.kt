package com.reift.kultum.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.reift.core.domain.model.Kultum
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding as FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        viewModel.getKultumForYou().observe(viewLifecycleOwner){
            setUpKultumViewPager(it)
            Toast.makeText(context, "Runned", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setUpKultumViewPager(listKultum: List<Kultum>) {
        binding.vpKultum.adapter = activity?.let { KultumViewPagerAdapter(it.supportFragmentManager, listKultum) }
    }

}