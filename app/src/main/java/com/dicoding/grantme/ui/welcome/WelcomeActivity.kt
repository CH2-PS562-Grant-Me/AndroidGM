package com.dicoding.grantme.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.grantme.R
import com.dicoding.grantme.databinding.ActivityWelcomeBinding
import com.dicoding.grantme.ui.login.LoginActivity
import com.dicoding.grantme.ui.register.RegisterActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnDaftarWelcome.setOnClickListener {
            startActivity(RegisterActivity::class.java)
        }
        binding.btnLoginWelcome.setOnClickListener {
            startActivity(LoginActivity::class.java)
        }
    }

    private fun startActivity(destination: Class<*>) {
        val intent = Intent(this, destination)
        startActivity(intent)
        overridePendingTransition(R.transition.slide_in_right, R.transition.slide_out_left)

    }
}