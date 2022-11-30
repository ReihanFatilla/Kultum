package com.reift.kultum.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reift.kultum.presentation.connect.fragment.ConnectTabFragment
import com.reift.kultum.presentation.profile.fragment.ProfileHelpfulFragment
import com.reift.kultum.presentation.profile.fragment.ProfileKultumFragment

class ConnectViewPagerAdapter(
    fa: FragmentActivity,
    val username: String
): FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        val profileHelpfulFragment = ConnectTabFragment()
        val helpfulBundle = Bundle()
        helpfulBundle.putString(BUNDLE_USERNAME, username)
        helpfulBundle.putString(BUNDLE_TYPE, TYPE_HELPFUL)

        profileHelpfulFragment.arguments = helpfulBundle

        val profileKultumFragment = ConnectTabFragment()
        val kultumBundle = Bundle()
        kultumBundle.putString(BUNDLE_USERNAME, username)
        kultumBundle.putString(BUNDLE_TYPE, TYPE_KULTUM)

        profileKultumFragment.arguments = kultumBundle


        return when(position){
            0 -> profileKultumFragment
            else -> profileHelpfulFragment
        }
    }

    companion object{
        const val BUNDLE_USERNAME = "bundle_username"
        const val BUNDLE_TYPE = "BUNDLE_TYPE"
        const val TYPE_KULTUM = "TYPE_KULTUM"
        const val TYPE_HELPFUL = "TYPE_HELPFUL"
    }
}