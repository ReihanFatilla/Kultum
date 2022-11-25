package com.reift.kultum.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.reift.kultum.MainActivity
import com.reift.kultum.databinding.ActivityLoginBinding
import com.reift.kultum.presentation.register.RegisterActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding as ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        setUpLoginFunction()
    }

    private fun setUpLoginFunction() {
        binding.apply {
            btnLogin.setOnClickListener {
                if (userInputIsValid()) {
                    loginValidationObserver()
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
            if (!edtEmail.text.contains("@")) {
                edtEmail.error = "Please use \"@\" for valid Email"
                edtEmail.requestFocus()
                return false
            }
            return true
        }
    }

    private fun loginValidationObserver() {
        binding.apply {
            viewModel.checkIfLoginValid(edtEmail.text.toString(), edtPassword.text.toString())
                .observe(this@LoginActivity) {
                    if (it) {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    } else {
                        Toast.makeText(applicationContext, "Incorrect Email or Password", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    private fun setUpView() {
        binding.tvLogin.setOnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        }
    }
}