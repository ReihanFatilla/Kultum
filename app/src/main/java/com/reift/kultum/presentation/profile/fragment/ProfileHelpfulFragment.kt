package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reift.kultum.R
import com.reift.kultum.databinding.FragmentProfileHelpfulBinding

class ProfileHelpfulFragment : Fragment() {

    private var _binding: FragmentProfileHelpfulBinding? = null
    private val binding get() = _binding as FragmentProfileHelpfulBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileHelpfulBinding.inflate(layoutInflater)
        return binding.root
    }
}