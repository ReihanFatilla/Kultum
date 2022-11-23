package com.reift.kultum.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.reift.kultum.presentation.home.fragment.KultumFragment

class KultumViewPagerAdapter(
    fm: FragmentManager,
    val url: List<String>,
    val totalVideo: Int
) : FragmentPagerAdapter(fm){

    override fun getCount()= totalVideo

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val kultumFragment = KultumFragment()
        bundle.putString(BUNDlE_VIDEO_URL, url[position])
        kultumFragment.arguments = bundle
        return kultumFragment
    }

    companion object{
        const val BUNDlE_VIDEO_URL = "BUNDlE_VIDEO_URL"
    }

}