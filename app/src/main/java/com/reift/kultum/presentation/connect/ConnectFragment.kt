package com.reift.kultum.presentation.connect

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.Kultum
import com.reift.core.domain.model.User
import com.reift.kultum.adapter.recyclerview.KultumAdapter
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
            svUser.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            viewModel.getUserByUsername(query).observe(viewLifecycleOwner){
                                if(it == null) return@observe
                                displaySearchedUser(it)
                            }
                        }
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }

                }
            )
        }
    }

    private fun displaySearchedUser(user: User) {
        binding.apply {
            profileContainer.visibility = View.VISIBLE

            with(user){
                tvName.text = name
                tvUsername.text = username
                Glide.with(this@ConnectFragment)
                    .load(photoUrl)
                    .apply(RequestOptions())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(imgProfile)


                viewModel.getPostedKultum(username).observe(viewLifecycleOwner){
                    setUpPostedKultumRV(it.take(3))
                }
            }
        }
    }

    private fun setUpPostedKultumRV(listKultum: List<Kultum>) {
        binding.rvKultum.apply {
            val mAdapter = KultumAdapter()
            adapter = mAdapter
            layoutManager = GridLayoutManager(context, 3)
            mAdapter.setKultum(listKultum)
        }
    }

}