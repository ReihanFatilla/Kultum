package com.reift.kultum.presentation.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
            tilEmail.helperText = ""
            tilPassword.helperText = ""
            tilUsername.helperText = ""
            if (edtEmail.text.isNullOrEmpty()) {
                tilEmail.helperText = "Please Fill your Email"
                edtEmail.requestFocus()
                return false
            }
            if (!edtEmail.text.toString().contains("@")) {
                tilEmail.helperText = "Please use \"@\" for valid Email"
                edtEmail.requestFocus()
                return false
            }
            if (edtUsername.text.isNullOrEmpty()) {
                tilUsername.helperText = "Please Enter Your Username"
                edtUsername.requestFocus()
                return false
            }
            if (edtPassword.text.isNullOrEmpty()) {
                tilPassword.helperText = "Please Fill your Password"
                edtPassword.requestFocus()
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
                    if (it) {
                        tilEmail.helperText = "Email is Taken"
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
                    val username = edtUsername.text.toString()
                    for(i in username.indices){
                        if(username[i].isUpperCase() || username[i].isDigit() || username[i].isWhitespace()){
                            Toast.makeText(applicationContext, "Username Cannot Contains Digit, Uppercase or White Space", Toast.LENGTH_SHORT).show()
                            return@observe
                        }
                    }
                    if (it) {
                        tilUsername.helperText = "Username is Taken"
                    } else {
                        viewModel.saveUser(
                            User(
                                username = edtUsername.text.toString(),
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