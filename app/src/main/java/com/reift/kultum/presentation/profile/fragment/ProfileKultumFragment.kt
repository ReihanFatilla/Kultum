package com.reift.kultum.presentation.profile.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.reift.core.domain.model.Kultum
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentProfileKultumBinding
import com.reift.kultum.presentation.profile.ProfileViewModel
import com.reift.kultum.presentation.profile.activity.ProfileShortsActivity
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
                        showKultumShorts(position)
                    }
                }
            )
        }
    }

    private fun showKultumShorts(position: Int) {

        startActivity(
            Intent(context, ProfileShortsActivity::class.java)
            .putExtra(Constant.EXTRA_TYPE, Constant.TYPE_KULTUM)
            .putExtra(Constant.EXTRA_POSITION, position)
        )
    }


}