package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.personalfinancemanager.R
import com.example.personalfinancemanager.databinding.ActivityStarted1Binding

class StartedActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityStarted1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarted1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.skipL.setOnClickListener(){
            startActivity(Intent(this@StartedActivity1,StartedActivity2::class.java))
            finish()
        }
        binding.btnNext.setOnClickListener(){
            startActivity(Intent(this@StartedActivity1,StartedActivity2::class.java))
            finish()
        }


    }
}