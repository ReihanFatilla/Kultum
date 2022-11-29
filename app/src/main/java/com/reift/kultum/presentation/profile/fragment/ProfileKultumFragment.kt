package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.databinding.FragmentProfileKultumBinding
import com.reift.kultum.presentation.profile.ProfileFragmentDirections
import com.reift.kultum.presentation.profile.ProfileViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileKultumFragment : Fragment() {

    private var _binding: FragmentProfileKultumBinding? = null
    private val binding get() = _binding as FragmentProfileKultumBinding

    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileKultumBinding.inflate(layoutInflater)

        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel.getPostedKultum().observe(viewLifecycleOwner){
            setUpKultum(it)
        }
    }

    private fun setUpKultum(listKultum: List<Kultum>) {
        binding.root.apply {
            val mAdapter = KultumAdapter()
            layoutManager = GridLayoutManager(context, 3)
            adapter = mAdapter
            mAdapter.setKultum(listKultum)
            mAdapter.setItemClickCallback(
                object : OnItemClickCallBack {
                    override fun onClick(position: Int) {
                        findNavController().navigate(
                            ProfileFragmentDirections.actionNavigationProfileToProfileShortsFragment(
                                ProfileShortsFragment.TYPE_KULTUM,
                                position
                            )
                        )
                    }
                }
            )
        }
    }

}