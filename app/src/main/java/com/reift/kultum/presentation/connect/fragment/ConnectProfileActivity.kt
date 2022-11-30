package com.reift.kultum.presentation.connect.fragment

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reift.core.domain.model.User
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.ProfileViewPagerAdapter
import com.reift.kultum.databinding.ActivityConnectProfileBinding
import com.reift.kultum.presentation.connect.ConnectFragment
import com.reift.kultum.presentation.connect.ConnectViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectProfileActivity : AppCompatActivity() {

	private var _binding: ActivityConnectProfileBinding? = null
	private val binding get() =  _binding as ActivityConnectProfileBinding

	private val viewModel: ConnectViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = ActivityConnectProfileBinding.inflate(layoutInflater)
		setContentView(binding.root)

		initObserver()
		setUpTabBar()

	}

	private fun initObserver() {
		val username = intent.getStringExtra(ConnectFragment.EXTRA_USERNAME) ?: ""
//		viewModel.getUserByUsername(username).observe(this){
//			setUpProfileDetail(it[0])
//		}
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
				Glide.with(this@ConnectProfileActivity)
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

			vpProfile.adapter = ProfileViewPagerAdapter(this@ConnectProfileActivity)

			TabLayoutMediator(profileTab, vpProfile){ tab, position ->
				when(position){
					0 -> tab.icon = getDrawable(R.drawable.ic_book)
					1 -> tab.icon = getDrawable(R.drawable.ic_helpful_inactive)
				}
			}.attach()

			profileTab.setOnTabSelectedListener(
				object : TabLayout.OnTabSelectedListener {

					override fun onTabSelected(tab: TabLayout.Tab) {
						val tabIconColor =
							ContextCompat.getColor(applicationContext, R.color.primary_color)
						tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
					}

					override fun onTabUnselected(tab: TabLayout.Tab) {
						val tabIconColor =
							ContextCompat.getColor(applicationContext, R.color.gray_text)
						tab.icon!!.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
					}

					override fun onTabReselected(tab: TabLayout.Tab) {

					}
				}
			)
		}

	}
}