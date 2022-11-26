package com.reift.kultum.presentation.edit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reift.kultum.R
import com.reift.kultum.databinding.ActivityEditBinding
import com.reift.kultum.presentation.edit.dialog.PhotoUrlDialogFragment

class EditActivity : AppCompatActivity() {

    private var _binding: ActivityEditBinding? = null
    private val binding get() = _binding as ActivityEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()

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