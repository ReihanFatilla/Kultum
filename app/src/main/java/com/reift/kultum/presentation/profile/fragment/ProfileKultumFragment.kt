package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.reift.kultum.R
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.databinding.FragmentProfileKultumBinding

class ProfileKultumFragment : Fragment() {

    private var _binding: FragmentProfileKultumBinding? = null
    private val binding get() = _binding as FragmentProfileKultumBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileKultumBinding.inflate(layoutInflater)

        setUpKultum()
        return binding.root
    }

    private fun setUpKultum() {
        binding.root.apply {
            val mAdapter = KultumAdapter()
            layoutManager = GridLayoutManager(context, 3)
            adapter = mAdapter
            mAdapter.setKultum(
                listOf(
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample,
                    R.drawable.img_profile_sample
                )
            )
        }
    }

}