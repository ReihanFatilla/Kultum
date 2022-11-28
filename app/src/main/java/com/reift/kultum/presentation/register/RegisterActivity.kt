package com.reift.kultum.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.reift.core.domain.model.User
import com.reift.kultum.databinding.ActivityRegisterBinding
import com.reift.kultum.presentation.edit.EditActivity
import com.reift.kultum.presentation.login.LoginActivity
import org.koin.android.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity() {

    private val viewModel: RegisterViewModel by viewModel()

    private var _binding: ActivityRegisterBinding? = null
    private val binding get() = _binding as ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setUpRegisterFunction()
    }

    private fun setUpRegisterFunction() {
        binding.apply {
            btnRegis.setOnClickListener {
                if (userInputIsValid()) {
                    emailValidationObserver()
                }
            }
        }
    }

    private fun userInputIsValid(): Boolean {
        binding.apply {
            if (edtEmail.text.isNullOrEmpty()) {
                edtEmail.error = "Please Fill your Email"
                edtEmail.requestFocus()
                return false
            }
            if (edtPassword.text.isNullOrEmpty()) {
                edtPassword.error = "Please Fill your Password"
                edtPassword.requestFocus()
                return false
            }
            if (edtUsername.text.isNullOrEmpty()) {
                edtUsername.error = "Please Enter Your Username"
                edtUsername.requestFocus()
                return false
            }
            if (!edtEmail.text.contains("@")) {
                edtEmail.error = "Please use \"@\" for valid Email"
                edtEmail.requestFocus()
                return false
            }
            return true
        }
    }

    private fun setUpView() {
        binding.tvLogin.setOnClickListener {
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }
    }

    private fun emailValidationObserver() {
        binding.apply {
            viewModel.checkIfEmailTaken(edtEmail.text.toString())
                .observe(this@RegisterActivity) {
                    Log.i("emailValidationObserverAA", "1: $it")
                    if (it) {
                        edtEmail.error = "Email is Taken"
                    } else {
                        userNameValidationObserver()
                    }
                }
        }
    }

    private fun userNameValidationObserver() {
        binding.apply {
            viewModel.checkIfUsernameTaken(edtUsername.text.toString())
                .observe(this@RegisterActivity) {
                    Log.i("emailValidationObserverAA", "2: $it")
                    if (it) {
                        edtUsername.error = "Username is Taken"
                    } else {
                        viewModel.saveUser(
                            User(
                                usernname = edtUsername.text.toString(),
                                email = edtEmail.text.toString(),
                                password = edtPassword.text.toString()
                            )
                        )
                        startActivity(
                            Intent(this@RegisterActivity, EditActivity::class.java)
                        )
                        finish()
                    }
                }
        }
    }

}