package com.nsicyber.deezerpractice

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            //MainActivity'e geçiş yapma
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            //SplashActivity'i kapatma
            finish()
        }, 3000)



    }
}