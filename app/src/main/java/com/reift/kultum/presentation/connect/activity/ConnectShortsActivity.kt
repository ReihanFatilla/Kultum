package com.reift.kultum.presentation.connect.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.reift.core.domain.model.Kultum
import com.reift.kultum.adapter.viewpager.KultumViewPagerAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.ActivityProfileShortsBinding
import com.reift.kultum.presentation.connect.ConnectViewModel
import com.reift.kultum.utils.Transparent
import org.koin.android.viewmodel.ext.android.viewModel


class ConnectShortsActivity : AppCompatActivity() {

    private val viewModel: ConnectViewModel by viewModel()

    private var _binding: ActivityProfileShortsBinding? = null
    private val binding get() = _binding as ActivityProfileShortsBinding

    private lateinit var type: String
    private lateinit var username: String
    private var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProfileShortsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Transparent.statusbar(this)

        type = intent.getStringExtra(Constant.EXTRA_TYPE).orEmpty()
        username = intent.getStringExtra(Constant.EXTRA_USERNAME).orEmpty()
        currentPosition = intent.getIntExtra(Constant.EXTRA_POSITION, 0)

        initObservers()
        binding.btnBack.setOnClickListener {
            backToProfile()
        }

    }

    private fun initObservers() {
        when(type){
            Constant.TYPE_KULTUM -> {
                viewModel.getPostedKultum(username).observe(this){
                    setUpKultumViewPager(it)
                }
            }
            Constant.TYPE_HELPFUL -> {
                viewModel.getHelpfulKultum(username).observe(this){
                    setUpKultumViewPager(it)
                }
            }
        }
    }

    private fun setUpKultumViewPager(listKultum: List<Kultum>) {
        val mAdapter =  KultumViewPagerAdapter(this, listKultum)

        binding.vpProfileKultum.adapter = mAdapter
        binding.vpProfileKultum.currentItem = currentPosition
    }

    private fun backToProfile() {
        finish()
    }

}