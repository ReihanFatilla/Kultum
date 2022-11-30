package com.reift.kultum.presentation.connect

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.reift.core.domain.model.User
import com.reift.kultum.`interface`.GetUserKultumCallBack
import com.reift.kultum.adapter.recyclerview.KultumAdapter
import com.reift.kultum.adapter.recyclerview.UserConnectAdapter
import com.reift.kultum.databinding.FragmentConnectBinding
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
            mAdapter.setUserKultumCallBack(
                object : GetUserKultumCallBack{
                    override fun getUserKultum(rvKultum: RecyclerView, username: String) {
                        viewModel.getPostedKultum(username).observe(viewLifecycleOwner){
                            rvKultum.apply {
                                val kutlumAdapter = KultumAdapter()
                                adapter = kutlumAdapter
                                layoutManager = GridLayoutManager(context, 3)
                                kutlumAdapter.setKultum(it)
                            }
                        }
                    }
                }
            )

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