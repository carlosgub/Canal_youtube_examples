package com.carlosgub.splashapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

//        Handler(Looper.getMainLooper()).postDelayed({
//                startActivity(MainActivity.newInstance(this@SplashActivity))
//            },2_000)

        GlobalScope.launch {
            delay(2_000)
            startActivity(MainActivity.newInstance(this@SplashActivity))
        }
    }
}