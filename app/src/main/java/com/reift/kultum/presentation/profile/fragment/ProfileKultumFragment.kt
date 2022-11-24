package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reift.kultum.R
import com.reift.kultum.databinding.FragmentProfileKultumBinding

class ProfileKultumFragment : Fragment() {

    private var _binding : FragmentProfileKultumBinding? = null
    private val binding get() = _binding as FragmentProfileKultumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileKultumBinding.inflate(layoutInflater)
        return binding.root
    }

}