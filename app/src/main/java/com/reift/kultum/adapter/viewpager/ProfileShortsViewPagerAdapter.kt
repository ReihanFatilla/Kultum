package com.reift.kultum.adapter.viewpager

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar
import com.reift.core.domain.model.Kultum
import com.reift.kultum.presentation.connect.ConnectFragment
import com.reift.kultum.presentation.home.fragment.KultumFragment
import com.reift.kultum.presentation.profile.fragment.ProfileKultumContainerFragment

class ProfileShortsViewPagerAdapter(
    fm: FragmentManager,
    val listKultum: List<Kultum>
) : FragmentPagerAdapter(fm){

    override fun getCount() = listKultum.size

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val kultumFragment = ProfileKultumContainerFragment()
        bundle.putParcelable(BUNDLE_KULTUM, listKultum[position])
        kultumFragment.arguments = bundle
        return kultumFragment
    }

    companion object{
        const val BUNDLE_KULTUM = "BUNDLE_KULTUM"
    }

}