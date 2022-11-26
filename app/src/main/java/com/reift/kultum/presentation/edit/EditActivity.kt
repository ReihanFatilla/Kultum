package com.reift.kultum.presentation.edit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.User
import com.reift.kultum.databinding.ActivityEditBinding
import com.reift.kultum.presentation.edit.dialog.PhotoUrlDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    private var _binding: ActivityEditBinding? = null
    private val binding get() = _binding as ActivityEditBinding

    private val viewModel: EditViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        initUserProfileObserver()

    }

    private fun initUserProfileObserver() {
        viewModel.getCurrentUser().observe(this) {
            setUpUserProfile(it)
        }
    }

    private fun setUpUserProfile(user: User) {
        binding.apply {
            with(user) {
                tvNameFill.setText(name)
                tvBioFill.setText(bio)
                tvUsernameFill.setText(usernname)

                Glide.with(this@EditActivity)
                    .load(photoUrl)
                    .apply(RequestOptions())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .priority(Priority.HIGH)
                    .into(imgProfile)
            }
        }
    }

    private fun setUpView() {
        binding.tvChangePhoto.setOnClickListener {
            showPhotoUrlDialog()
        }
    }

    private fun showPhotoUrlDialog() {
        PhotoUrlDialogFragment().show(supportFragmentManager, PhotoUrlDialogFragment.TAG)
    }


}