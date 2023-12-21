package com.dicoding.grantme.ui.landing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dicoding.grantme.R
import com.dicoding.grantme.databinding.ActivityLandingBinding
import com.dicoding.grantme.di.Injection
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.main.MainActivity
import com.dicoding.grantme.ui.main.MainViewModel
import com.dicoding.grantme.ui.welcome.WelcomeActivity

class LandingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            navigateToWelcomeActivity()
        }
        val userRepository = Injection.provideRepository(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[MainViewModel::class.java]
        viewModel.getSession().observe(this) { loginResponse ->
            loginResponse?.isLogin?.let { userResult ->
                if (userResult) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
    }

}