package com.dicoding.grantme.ui.login

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dicoding.grantme.R
import com.dicoding.grantme.databinding.ActivityLoginBinding
import com.dicoding.grantme.ui.DataDiriActivity
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.probarLogin.visibility = View.GONE
        setupView()
        setupAction()
       // playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.btnlogin.setOnClickListener {
            val logEmail = binding.logEmail.text.toString()
            val logPass = binding.logKatasandi.text.toString()
            binding.btnlogin.isEnabled = true

            try {
                viewModel.login(logEmail, logPass)
            } catch (e: Exception) {
                showErrorDialog("Pengguna tidak ditemukan")
                e.printStackTrace()
            }
        }
        viewModel.getSession().observe(this@LoginActivity) { loginResponse ->
            loginResponse?.isLogin?.let { userResult ->
                if (userResult) {
                    binding.probarLogin.visibility = View.VISIBLE
                    val intent = Intent(this@LoginActivity, DataDiriActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        viewModel.errorLiveData.observe(this@LoginActivity) { errorMessage ->
            if (!errorMessage.isNullOrBlank()) {
                AlertDialog.Builder(this@LoginActivity).apply {
                    setTitle("Gagal masuk!")
                    setMessage(errorMessage)
                    setPositiveButton("Ok") { _, _ ->

                        finish()
                    }
                    create()
                    show()
                }
                binding.btnlogin.isEnabled = true
            }else{
                AlertDialog.Builder(this@LoginActivity).apply {
                    setTitle("Berhasil!")
                    setMessage(errorMessage)
                    setPositiveButton("Lanjut") { _, _ ->
                        finish()
                    }
                    create()
                    show()
                }
            }
        }
    }
    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(this@LoginActivity).apply {
            setTitle("Error")
            setMessage(message)
            setPositiveButton("Ok") { _, _ ->
                finish()
            }
            create()
            show()
        }
    }
}