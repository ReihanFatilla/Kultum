package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.adapter.viewpager.ProfileShortsViewPagerAdapter
import com.reift.kultum.databinding.FragmentHomeBinding
import com.reift.kultum.databinding.FragmentProfileShortsBinding
import com.reift.kultum.presentation.home.HomeViewModel
import com.reift.kultum.presentation.profile.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileShortsFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

    private var _binding: FragmentProfileShortsBinding? = null
    private val binding get() = _binding as FragmentProfileShortsBinding

    private lateinit var type: String
    private var currentPosition = 0

    private val args: ProfileShortsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileShortsBinding.inflate(layoutInflater)

        type = args.type
        currentPosition = args.currentPosition

        initObservers()

        return binding.root
    }

    private fun initObservers() {
        when(type){
            TYPE_KULTUM -> {
                viewModel.getPostedKultum().observe(viewLifecycleOwner){
                    setUpKultumViewPager(it)
                }
            }
            TYPE_HELPFUL -> {
                viewModel.getHelpfuledKultum().observe(viewLifecycleOwner){
                    setUpKultumViewPager(it)
                }
            }
        }
    }

    private fun setUpKultumViewPager(listKultum: List<Kultum>) {
        val mAdapter =  ProfileShortsViewPagerAdapter(requireActivity().supportFragmentManager, listKultum)
        binding.vpProfileKultum.adapter = mAdapter
        binding.vpProfileKultum.currentItem = currentPosition
        Log.i("getItemAAAA", "$mAdapter")
    }

    companion object{
        const val TYPE_KULTUM = "type_kultum"
        const val TYPE_HELPFUL = "type_helpful"
    }

}