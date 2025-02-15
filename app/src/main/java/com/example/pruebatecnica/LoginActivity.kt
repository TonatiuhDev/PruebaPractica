package com.example.pruebatecnica

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.pruebatecnica.commons.validateEmail
import com.example.pruebatecnica.commons.validatePassword
import com.example.pruebatecnica.databinding.ActivityLoginBinding
import com.example.pruebatecnica.home.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        binding.loginButton.setOnClickListener {
            if (validateAllFields())
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun validateAllFields(): Boolean {
        val validatePassword = binding.passwordEditText.validatePassword(
            getString(R.string.password_error_hint), binding.passwordTextLayout
        )
        val validateEmail = binding.emailEditText.validateEmail(
            getString(R.string.email_error_login), binding.emailTextLayout
        )
        return (validatePassword && validateEmail)
    }
}