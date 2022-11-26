package com.reift.kultum.presentation.edit.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.reift.kultum.R
import com.reift.kultum.databinding.ActivityEditBinding
import com.reift.kultum.databinding.FragmentPhotoUrlDialogBinding

class PhotoUrlDialogFragment : DialogFragment() {

    private var _binding: FragmentPhotoUrlDialogBinding? = null
    private val binding get() = _binding as FragmentPhotoUrlDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoUrlDialogBinding.inflate(layoutInflater)
        setUpView()
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setUpView() {
        binding.edtImageUrl.setOnClickListener {
            dismiss()
        }
    }

    companion object {

        const val TAG = "PHOTO_URL_DIALOG"

    }


}