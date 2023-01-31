package com.reift.kultum.adapter.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar
import com.reift.core.domain.model.Kultum
import com.reift.kultum.presentation.home.fragment.KultumFragment

class KultumViewPagerAdapter(
    fa: FragmentActivity,
    val listKultum: List<Kultum>
) : FragmentStateAdapter(fa){

    override fun getItemCount() = listKultum.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        val kultumFragment = KultumFragment()
        bundle.putParcelable(BUNDLE_KULTUM, listKultum[position])
        kultumFragment.arguments = bundle
        return kultumFragment
    }

    companion object{
        const val BUNDLE_KULTUM = "BUNDLE_KULTUM"
    }

}