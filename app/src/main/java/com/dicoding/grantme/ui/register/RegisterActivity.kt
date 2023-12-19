package com.dicoding.grantme.ui.register

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dicoding.grantme.data.ResultState
import com.dicoding.grantme.data.response.RegistResponse
import com.dicoding.grantme.databinding.ActivityRegisterBinding
import com.dicoding.grantme.ui.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressBar.visibility = View.GONE
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
        binding.btnRegis.setOnClickListener {
            val regEmail = binding.regEmail.text.toString()
            val regPass = binding.regSandi.text.toString()
            val regName = binding.regNama.text.toString()
            binding.btnRegis.isEnabled = true

            viewModel.register(regName, regEmail, regPass).observe(this@RegisterActivity) { result ->
                when (result) {
                    is ResultState.Loading -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is ResultState.Success<*> -> {
                        binding.progressBar.visibility = View.VISIBLE
                        val response: RegistResponse = result.data as RegistResponse
                        AlertDialog.Builder(this).apply {
                            setTitle("Berhasil!")
                            setMessage(response.message)
                            setPositiveButton("Lanjut") { _, _ ->
                                finish()
                            }
                            create()
                            show()
                        }
                        binding.btnRegis.isEnabled = true
                    }
                    is ResultState.Error -> {
                        val errorMessage: String? = result.error
                        AlertDialog.Builder(this).apply {
                            setTitle("Gagal!")
                            setMessage(errorMessage)
                            setPositiveButton("Lanjut") { _, _ ->
                            }
                            create()
                            show()
                        }
                        binding.btnRegis.isEnabled = true
                    }
                }
            }
        }
    }
}