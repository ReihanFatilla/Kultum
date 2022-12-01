package com.reift.kultum.presentation.connect.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.reift.core.domain.model.Kultum
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.adapter.viewpager.ConnectViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentConnectTabBinding
import com.reift.kultum.presentation.connect.ConnectViewModel
import com.reift.kultum.presentation.profile.ProfileFragmentDirections
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectTabFragment : Fragment() {

    private var _binding: FragmentConnectTabBinding? = null
    private val binding get() = _binding as FragmentConnectTabBinding

    private val viewModel: ConnectViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectTabBinding.inflate(layoutInflater)
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        val username = arguments?.getString(ConnectViewPagerAdapter.BUNDLE_USERNAME) ?: ""
        val type = arguments?.getString(ConnectViewPagerAdapter.BUNDLE_TYPE)
        when(type){
            ConnectViewPagerAdapter.TYPE_HELPFUL -> {
                viewModel.getHelpfulKultum(username).observe(viewLifecycleOwner){
                    setUpKultum(it)
                }
            }
            ConnectViewPagerAdapter.TYPE_KULTUM -> {
                viewModel.getPostedKultum(username).observe(viewLifecycleOwner){
                    setUpKultum(it)
                }
            }
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
                                Constant.TYPE_KULTUM,
                                position
                            )
                        )
                    }
                }
            )
        }
    }
}