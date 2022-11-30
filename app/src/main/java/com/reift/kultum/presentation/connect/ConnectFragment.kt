package com.reift.kultum.presentation.connect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.kultum.`interface`.GetUserKultumCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.adapter.recyclerview.UserConnectAdapter
import com.reift.kultum.databinding.FragmentConnectBinding
import com.reift.kultum.presentation.connect.fragment.ConnectProfileActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ConnectFragment : Fragment() {

    private val viewModel: ConnectViewModel by viewModel()

    private var _binding: FragmentConnectBinding? = null
    private val binding get() = _binding as FragmentConnectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentConnectBinding.inflate(layoutInflater)

        setUpSearchUser()
        return binding.root
    }

    private fun setUpSearchUser() {
        binding.apply {
            viewModel.getUserByUsername().observe(viewLifecycleOwner){
                if(it == null) return@observe
                setUpUserRV(it)
            }
            btnSearch.setOnClickListener {
                if (isQueryValid()) {
                    viewModel.getUserByUsername(svUser.text.toString()).observe(viewLifecycleOwner){
                        if(it == null) return@observe
                        setUpUserRV(it)
                    }
                }
            }

        }
    }

    private fun setUpUserRV(listUser: List<User>) {
        binding.rvUser.apply {
            val mAdapter = UserConnectAdapter()
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
            mAdapter.setUserList(listUser)
            viewModel.getPostedKultum(binding.svUser.text.toString()).observe(viewLifecycleOwner){
                Log.i("setUpUserRVAAA", "setUpUserRV: $it")
                mAdapter.setUserKultum(
                    object : GetUserKultumCallBack{
                        override fun getUserKultum(): List<Kultum> {
                            return it
                        }
                    }
                )
            }
        }
    }

    private fun isQueryValid(): Boolean {
        var isValid = false
        val username = binding.svUser.text.toString()
        for(i in username.indices){
            isValid = if(username[i].isUpperCase() || username[i].isDigit() || username[i].isWhitespace()){
                Toast.makeText(context, "Username Cannot Contains Digit, Uppercase or White Space", Toast.LENGTH_SHORT).show()
                false
            } else {
                true
            }
        }
        return isValid
    }

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

}