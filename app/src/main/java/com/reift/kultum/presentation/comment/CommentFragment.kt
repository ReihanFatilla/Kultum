package com.reift.kultum.presentation.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.reift.core.domain.model.Comments
import com.reift.kultum.R
import com.reift.kultum.adapter.recyclerview.CommentAdapter
import com.reift.kultum.constant.Constant
import com.reift.kultum.databinding.FragmentCommentBinding
import com.reift.kultum.databinding.FragmentPhotoUrlDialogBinding
import com.reift.kultum.presentation.edit.EditViewModel
import com.reift.kultum.presentation.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CommentFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCommentBinding? = null
    private val binding get() = _binding as FragmentCommentBinding

    private val viewModel: CommentViewModel by viewModel()

    lateinit var urlKultum: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCommentBinding.inflate(layoutInflater)

        urlKultum = arguments?.getString(Constant.BUNDLE_URL_KULTUM).orEmpty()

        setUpView()
        initObserver()

        return binding.root
    }

    private fun setUpView() {
        binding.apply {
            btnSendComment.setOnClickListener {
                if(isMessageValid()){
                    val message = edtMessage.text.toString()
                    viewModel.addComment(message, urlKultum)
                }
            }
            btnClose.setOnClickListener {
                dismiss()
            }
        }
    }

    private fun isMessageValid(): Boolean {
        binding.edtMessage.text.apply {
            return if(isNullOrEmpty()){
                false
            } else {
                true
            }
        }
    }

    private fun initObserver() {
        viewModel.getKultumComments(urlKultum).observe(viewLifecycleOwner){
            displayComments(it)
        }
    }

    private fun displayComments(listComment: List<Comments>) {
        binding.apply {
            tvTotal.text = listComment.size.toString()
            rvComment.apply {
                val mAdapter = CommentAdapter()
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
                mAdapter.setComments(listComment)
            }
        }
    }

}