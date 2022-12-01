package com.reift.kultum.presentation.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.reift.kultum.R
import com.reift.kultum.databinding.FragmentCommentBinding
import com.reift.kultum.databinding.FragmentPhotoUrlDialogBinding
import com.reift.kultum.presentation.edit.EditViewModel
import com.reift.kultum.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CommentFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding as FragmentCommentBinding

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentBinding.inflate(layoutInflater)
        return binding.root
    }
}