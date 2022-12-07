package com.reift.kultum

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.reift.kultum.databinding.ActivityMainBinding
import com.reift.kultum.presentation.connect.ConnectFragment
import com.reift.kultum.presentation.home.HomeFragment
import com.reift.kultum.presentation.post.PostActivity
import com.reift.kultum.presentation.profile.ProfileFragment
import com.reift.kultum.utils.StatusBar
import com.reift.kultum.utils.Transparent


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Transparent.statusbar(this)

        binding.btnNavPost.setOnClickListener {
            startActivity(Intent(this, PostActivity::class.java))
            finish()
        }


        val fragment1: Fragment = HomeFragment()
        val fragment2: Fragment = ConnectFragment()
        val fragment3: Fragment = ProfileFragment()
        val fm: FragmentManager = supportFragmentManager
        var active: Fragment = fragment1


        fm.beginTransaction().add(R.id.main_container, fragment3, "profile").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.main_container, fragment2, "connect").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.main_container,fragment1, "home").commit();
        StatusBar.setLightIcon(this)

        val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_home -> {
                        fm.beginTransaction().hide(active).show(fragment1).commit()
                        active = fragment1
                        StatusBar.setLightIcon(this)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_connect -> {
                        fm.beginTransaction().hide(active).show(fragment2).commit()
                        active = fragment2
                        StatusBar.setDarkIcon(this)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_profile -> {
                        fm.beginTransaction().hide(active).show(fragment3).commit()
                        active = fragment3
                        StatusBar.setDarkIcon(this)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.navigation_chat -> {
                        Toast.makeText(applicationContext, "Chat is Coming Soon!", Toast.LENGTH_SHORT).show()
                    }
                }
                false
            }

        binding.bottomNav.setOnNavigationItemSelectedListener(
            mOnNavigationItemSelectedListener
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}