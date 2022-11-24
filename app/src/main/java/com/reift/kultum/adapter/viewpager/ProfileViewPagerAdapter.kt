package com.reift.kultum.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reift.kultum.presentation.profile.fragment.ProfileHelpfulFragment
import com.reift.kultum.presentation.profile.fragment.ProfileKultumFragment

class ProfileViewPagerAdapter(
    fa: FragmentActivity
): FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ProfileKultumFragment()
            else -> ProfileHelpfulFragment()
        }
    }
}