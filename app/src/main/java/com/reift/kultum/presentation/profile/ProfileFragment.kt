package com.reift.kultum.presentation.profile

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.core.domain.model.User
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.ProfileViewPagerAdapter
import com.reift.kultum.databinding.FragmentProfileBinding
import com.reift.kultum.presentation.edit.EditActivity
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModel()

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding as FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater)

        setUpTabBar()
        initObserver()
        setUpView()

        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            btnEditProfile.setOnClickListener {
                startActivity(Intent(context, EditActivity::class.java))
            }
        }
    }

    private fun initObserver() {
        viewModel.getUserDetail().observe(viewLifecycleOwner){
            setUpProfileDetail(it)
        }
    }

    private fun setUpProfileDetail(user: User) {
        binding.apply {
            with(user){
                tvUsername.text = "@$username"
                tvName.text = name
                tvBio.text = bio
                tvFollowersAmount.text = followers.size.toString()
                tvFollowingAmount.text = followers.size.toString()
                tvKultumAmount.text = kultum.size.toString()
                Glide.with(this@ProfileFragment)
                    .load(photoUrl)
                    .apply(RequestOptions())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(imgProfile)
            }
        }
    }

    private fun setUpTabBar() {

        binding.apply {

            vpProfile.adapter = activity?.let { ProfileViewPagerAdapter(it) }

            TabLayoutMediator(profileTab, vpProfile){ tab, position ->
                when(position){
                    0 -> tab.icon = context?.getDrawable(R.drawable.ic_book)
                    1 -> tab.icon = context?.getDrawable(R.drawable.ic_helpful_inactive)
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