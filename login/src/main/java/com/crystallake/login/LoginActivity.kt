package com.crystallake.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        println("savedInstanceState:${savedInstanceState?.get("test")}")
        println("intent:${intent.getStringExtra("test")}")
        println("intent:${intent.extras?.get("test")}")

    }
}