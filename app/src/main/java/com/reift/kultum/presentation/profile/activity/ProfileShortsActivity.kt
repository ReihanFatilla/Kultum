package com.reift.kultum.presentation.profile.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.reift.core.domain.model.Kultum
import com.reift.kultum.R
import com.reift.kultum.adapter.viewpager.ProfileShortsViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.ActivityProfileShortsBinding
import com.reift.kultum.presentation.profile.ProfileFragment
import com.reift.kultum.presentation.profile.ProfileViewModel
import com.reift.kultum.utils.Transparent
import org.koin.android.viewmodel.ext.android.viewModel


class ProfileShortsActivity : AppCompatActivity() {

    private val viewModel: ProfileViewModel by viewModel()

    private var _binding: ActivityProfileShortsBinding? = null
    private val binding get() = _binding as ActivityProfileShortsBinding

    private lateinit var type: String
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileShortsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Transparent.statusbar(this)

        type = intent.getStringExtra(Constant.EXTRA_TYPE) ?: Constant.TYPE_KULTUM
        currentPosition = intent.getIntExtra(Constant.EXTRA_POSITION, 0)

        initObservers()
        binding.btnBack.setOnClickListener {
            backToProfile()
        }

    }

    private fun initObservers() {
        when(type){
            Constant.TYPE_KULTUM -> {
                viewModel.getPostedKultum().observe(this){
                    setUpKultumViewPager(it)
                }
            }
            Constant.TYPE_HELPFUL -> {
                viewModel.getHelpfuledKultum().observe(this){
                    setUpKultumViewPager(it)
                }
            }
        }
    }

    private fun setUpKultumViewPager(listKultum: List<Kultum>) {
        val mAdapter =  ProfileShortsViewPagerAdapter(supportFragmentManager, listKultum)

        binding.vpProfileKultum.adapter = mAdapter
        binding.vpProfileKultum.currentItem = currentPosition
    }

    private fun backToProfile() {
        finish()
    }

}