package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancemanager.activities.sharedPref.MySharedPref
import com.example.personalfinancemanager.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mySharedPref: MySharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mySharedPref = MySharedPref(this)
        binding.btnRegister.setOnClickListener(){
            startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            finish()
        }
        binding.btnLogin.setOnClickListener(){
            if (binding.etEmail.text.toString() == mySharedPref.getPref("email") &&
                binding.etPassword.text.toString() == mySharedPref.getPref("password")){
                mySharedPref.setLoggedIn(true)
                startActivity(Intent(this@LoginActivity,HomeActivity::class.java))
                finish()
                Toast.makeText(this@LoginActivity, "Logged In", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT).show()
            }

        }


    }
}