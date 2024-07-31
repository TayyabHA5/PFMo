package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancemanager.activities.sharedPref.MySharedPref
import com.example.personalfinancemanager.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private lateinit var mySharedPref: MySharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mySharedPref = MySharedPref(this)
        Handler().postDelayed(Runnable {
            if (mySharedPref.isLoggedIn()){
                startActivity(Intent(this@SplashActivity,HomeActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this@SplashActivity,StartedActivity1::class.java))
                finish()
            }

        },3000)

    }
}