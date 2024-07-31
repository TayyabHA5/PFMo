package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.personalfinancemanager.R
import com.example.personalfinancemanager.databinding.ActivityStarted2Binding

class StartedActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityStarted2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityStarted2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipL.setOnClickListener(){
            startActivity(Intent(this@StartedActivity2,GetStartedActivity::class.java))
            finish()
        }
        binding.btnNext.setOnClickListener(){
            startActivity(Intent(this@StartedActivity2,GetStartedActivity::class.java))
            finish()
        }

    }
}