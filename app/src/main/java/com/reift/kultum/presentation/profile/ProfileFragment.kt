package com.reift.kultum.presentation.profile

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.ProfileViewPagerAdapter
import com.reift.kultum.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(layoutInflater)

        setUpTabBar()

        return binding.root
    }

    private fun setUpTabBar() {

        binding.apply {

            vpProfile.adapter = activity?.let { ProfileViewPagerAdapter(it) }

            TabLayoutMediator(profileTab, vpProfile){ tab, position ->
                when(position){
                    0 -> tab.icon = context?.getDrawable(R.drawable.ic_book)
                    1 -> tab.icon = context?.getDrawable(R.drawable.ic_love)
                }
            }.attach()

            profileTab.setOnTabSelectedListener(
                object : TabLayout.OnTabSelectedListener {

                    override fun onTabSelected(tab: TabLayout.Tab) {
                        val tabIconColor =
                            ContextCompat.getColor(context!!, R.color.primary_color)
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab) {
                        val tabIconColor =
                            ContextCompat.getColor(context!!, R.color.gray_text)
                        tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
                    }

                    override fun onTabReselected(tab: TabLayout.Tab) {

                    }
                }
            )
        }

    }

}