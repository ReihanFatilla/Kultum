package com.reift.kultum.presentation.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.reift.kultum.MainActivity
import com.reift.kultum.R
import com.reift.kultum.presentation.edit.EditActivity
import com.reift.kultum.presentation.login.LoginActivity
import com.reift.kultum.presentation.register.RegisterActivity
import org.koin.android.viewmodel.ext.android.viewModel


class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            viewModel.isLogin.observe(this){
                if(it){
                    startActivity(
                        Intent(this, MainActivity::class.java)
                    )
                    finish()
                } else {
                    startActivity(
                        Intent(this, LoginActivity::class.java)
                    )
                    finish()
                }
            }
        }, 2000)


    }
}