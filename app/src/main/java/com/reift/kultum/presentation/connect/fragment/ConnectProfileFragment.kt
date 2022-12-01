package com.reift.kultum.presentation.connect.fragment

import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.reift.kultum.adapter.viewpager.ConnectViewPagerAdapter
import com.reift.kultum.adapter.viewpager.ProfileViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentConnectProfileBinding
import com.reift.kultum.presentation.connect.ConnectViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectProfileFragment : Fragment() {

	private var _binding: FragmentConnectProfileBinding? = null
	private val binding get() =  _binding as FragmentConnectProfileBinding

	private val viewModel: ConnectViewModel by viewModel()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentConnectProfileBinding.inflate(layoutInflater)

		initObserver()

		return binding.root
	}

	private fun initObserver() {
		val username = arguments?.getString(Constant.BUNDLE_USERNAME) ?: ""
		viewModel.getUserByUsername(username).observe(viewLifecycleOwner){
			setUpProfileDetail(it[0])
			setUpTabBar(it[0].username)
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
				tvKultumAmount.text = kultumAmount
				Glide.with(this@ConnectProfileFragment)
					.load(photoUrl)
					.apply(RequestOptions())
					.diskCacheStrategy(DiskCacheStrategy.ALL)
					.priority(Priority.HIGH)
					.into(imgProfile)
			}
		}
	}

	private fun setUpTabBar(username: String) {

		binding.apply {

			vpProfile.adapter = ConnectViewPagerAdapter(requireActivity(), username)

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
							context?.let { ContextCompat.getColor(it, R.color.primary_color) }
						tabIconColor?.let { tab.icon?.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
					}

					override fun onTabUnselected(tab: TabLayout.Tab) {
						val tabIconColor =
							context?.let { ContextCompat.getColor(it, R.color.gray_text) }
						tabIconColor?.let { tab.icon?.setColorFilter(it, PorterDuff.Mode.SRC_IN) }
					}

					override fun onTabReselected(tab: TabLayout.Tab) {

					}
				}
			)
		}

	}
}