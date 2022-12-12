package com.reift.kultum.presentation.connect.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.reift.core.domain.model.Kultum
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.adapter.viewpager.ConnectViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentConnectTabBinding
import com.reift.kultum.presentation.connect.ConnectViewModel
import com.reift.kultum.presentation.connect.activity.ConnectShortsActivity
import com.reift.kultum.presentation.profile.activity.ProfileShortsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectTabFragment : Fragment() {

    private var _binding: FragmentConnectTabBinding? = null
    private val binding get() = _binding as FragmentConnectTabBinding

    private val viewModel: ConnectViewModel by viewModel()

    private lateinit var type: String
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectTabBinding.inflate(layoutInflater)

        type = arguments?.getString(ConnectViewPagerAdapter.BUNDLE_TYPE).orEmpty()
        username = arguments?.getString(ConnectViewPagerAdapter.BUNDLE_USERNAME).orEmpty()


        initObserver()
        return binding.root
    }

    private fun initObserver() {

        when(type){
            ConnectViewPagerAdapter.TYPE_HELPFUL -> {
                viewModel.getHelpfulKultum(username).observe(viewLifecycleOwner){
                    setUpKultum(it)
                    Log.i("initObserverinitObserver", "initObserver: $it")
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
            onKultumClicked(mAdapter)

        }
    }

    private fun onKultumClicked(mAdapter: KultumAdapter) {
        mAdapter.setItemClickCallback(
            object : OnItemClickCallBack {
                override fun onClick(position: Int) {
                    showKultumShorts(position)
                }
            }
        )
    }

    private fun showKultumShorts(position: Int) {
        val intent = Intent(context, ConnectShortsActivity::class.java)
            .putExtra(Constant.EXTRA_POSITION, position)
        when(type){
            ConnectViewPagerAdapter.TYPE_HELPFUL -> {
                intent.putExtra(Constant.EXTRA_USERNAME, username)
                intent.putExtra(Constant.EXTRA_TYPE, Constant.TYPE_HELPFUL)
            }
            ConnectViewPagerAdapter.TYPE_KULTUM -> {
                intent.putExtra(Constant.EXTRA_USERNAME, username)
                intent.putExtra(Constant.EXTRA_TYPE, Constant.TYPE_KULTUM)
            }
        }
        startActivity(intent)
    }


}