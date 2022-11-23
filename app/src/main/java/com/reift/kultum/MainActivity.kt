package com.reift.kultum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.reift.kultum.utils.Transparent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Transparent.statusbar(this)
    }
}