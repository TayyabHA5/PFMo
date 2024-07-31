package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.personalfinancemanager.R
import com.example.personalfinancemanager.databinding.ActivityGetStartedBinding

class GetStartedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGetStartedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGetStartedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipL.setOnClickListener(){
            startActivity(Intent(this@GetStartedActivity,LoginActivity::class.java))
            finish()
        }
        binding.btnGetStarted.setOnClickListener(){
            startActivity(Intent(this@GetStartedActivity,LoginActivity::class.java))
            finish()
        }
    }
}