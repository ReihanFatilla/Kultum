package com.reift.kultum.presentation.edit.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.reift.kultum.databinding.FragmentPhotoUrlDialogBinding
import com.reift.kultum.presentation.edit.EditViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoUrlDialogFragment : DialogFragment() {

    private var _binding: FragmentPhotoUrlDialogBinding? = null
    private val binding get() = _binding as FragmentPhotoUrlDialogBinding

    private val viewModel: EditViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        binding.apply {
            binding.btnConfirm.setOnClickListener {
                if (edtImageUrl.text.isNullOrEmpty()) {
                    edtImageUrl.requestFocus()
                    edtImageUrl.error = "Please fill the Url"
                } else if (edtImageUrl.text.contains("png") || edtImageUrl.text.contains("jpeg") || edtImageUrl.text.contains("jpg") || edtImageUrl.text.contains("profile-display")) {
                    viewModel.editPhotoUrl(edtImageUrl.text.toString())
                    dismiss()
                } else {
                    edtImageUrl.requestFocus()
                    edtImageUrl.setText("")
                    edtImageUrl.error = "Please choose the \"Copy Image Address\""
                }
            }
            btnCancel.setOnClickListener {
                dismiss()
            }
        }
    }

    companion object {

        const val TAG = "PHOTO_URL_DIALOG"

    }


}