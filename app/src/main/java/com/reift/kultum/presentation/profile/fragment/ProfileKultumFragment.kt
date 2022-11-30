package com.reift.kultum.presentation.profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.`interface`.OnItemClickCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.databinding.FragmentProfileKultumBinding
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
                        showKultumShorts(position)
                    }
                }
            )
        }
    }

    private fun showKultumShorts(position: Int) {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        val profileShortsFragment = requireActivity().supportFragmentManager.findFragmentByTag("4") ?: ProfileShortsFragment()
        val bundle = Bundle()
        bundle.putString(ProfileShortsFragment.BUNDLE_TYPE ,ProfileShortsFragment.TYPE_KULTUM)
        bundle.putInt(ProfileShortsFragment.BUNDLE_POSITION, position)
        profileShortsFragment.arguments = bundle

        val profileFragment = requireActivity().supportFragmentManager.findFragmentByTag("3") ?: ProfileShortsFragment()

        fragmentTransaction.hide(profileFragment).show(profileShortsFragment).commit()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.INVISIBLE
        requireActivity().findViewById<ImageView>(R.id.btn_nav_post).visibility = View.INVISIBLE
    }

}