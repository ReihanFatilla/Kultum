package com.reift.kultum.presentation.edit

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.reift.core.domain.model.User
import com.reift.kultum.MainActivity
import com.reift.kultum.databinding.ActivityEditBinding
import com.reift.kultum.presentation.edit.dialog.PhotoUrlDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class EditActivity : AppCompatActivity() {

    private var _binding: ActivityEditBinding? = null
    private val binding get() = _binding as ActivityEditBinding

    private val viewModel: EditViewModel by viewModel()

    private var user: User? = null

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
                this@EditActivity.user = user

                tvNameFill.setText(name)
                tvBioFill.setText(bio)
                tvUsernameFill.setText(username)


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
        binding.apply {
            btnSave.setOnClickListener {
                saveNewProfile()
            }
            btnLogout.setOnClickListener {

            }
            tvChangePhoto.setOnClickListener {
                showPhotoUrlDialog()
            }
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun saveNewProfile() {
        binding.apply {
            if(tvUsernameFill.text.toString() != user?.username){
                viewModel.checkIfUserTaken(tvUsernameFill.text.toString()).observe(this@EditActivity){
                    if(it){
                        tvUsernameFill.requestFocus()
                        Toast.makeText(applicationContext, "Username is Taken", Toast.LENGTH_SHORT).show()
                    } else{
                        viewModel.editUsername(tvUsernameFill.text.toString())
                    }
                }
            }
            if(tvBioFill.text.toString() != user?.bio){
                viewModel.editBio(tvBioFill.text.toString())
            }
            if(tvNameFill.text.toString() != user?.name){
                viewModel.editName(tvNameFill.text.toString())
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

    private fun showPhotoUrlDialog() {
        PhotoUrlDialogFragment().show(supportFragmentManager, PhotoUrlDialogFragment.TAG)
    }


}