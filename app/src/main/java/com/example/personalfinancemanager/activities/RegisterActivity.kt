package com.example.personalfinancemanager.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.personalfinancemanager.activities.sharedPref.MySharedPref
import com.example.personalfinancemanager.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var mySharedPref: MySharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mySharedPref = MySharedPref(this)
        binding.btnLogin.setOnClickListener(){
            startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
        }
        binding.btnRegister.setOnClickListener(){
           if (binding.etName.text.toString().isEmpty() ){
               binding.etName.error = "Required"
           }else if (binding.etEmail.text.toString().isEmpty()){
               binding.etEmail.error = "Required"
           }else if (binding.etPassword.text.toString().isEmpty()){
               binding.etPassword.error = "Required"
           }else if (binding.etConfirmPassword.text.toString().isEmpty()){
               binding.etConfirmPassword.error = "Required"
           }else{
               mySharedPref.addOrUpdate("email",binding.etEmail.text.toString())
               mySharedPref.addOrUpdate("password",binding.etPassword.text.toString())
               startActivity(Intent(this@RegisterActivity,LoginActivity::class.java))
               finish()
           }
        }
    }
}