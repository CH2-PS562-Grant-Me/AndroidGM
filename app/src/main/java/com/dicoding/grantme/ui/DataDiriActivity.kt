package com.dicoding.grantme.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.dicoding.grantme.R
import com.dicoding.grantme.data.ResultState
import com.dicoding.grantme.data.response.RegistResponse
import com.dicoding.grantme.databinding.ActivityDataDiriBinding
import com.dicoding.grantme.databinding.ActivityRegisterBinding
import com.dicoding.grantme.ui.main.MainActivity
import com.dicoding.grantme.ui.main.MainViewModel
import com.dicoding.grantme.ui.register.RegisterViewModel

class DataDiriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataDiriBinding
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataDiriBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
    }

    private fun setupAction() {
        binding.btnSimpan.setOnClickListener {
            val IPK = binding.dataIpk.text.toString().toFloat()
            val sertif = binding.dataSertif.text.toString().toFloat()
            val sertifpro = binding.dataSertifnasional.text.toString().toFloat()
            val presnasional = binding.dataPrestasi.text.toString().toFloat()
            val presinter = binding.dataPresinter.text.toString().toFloat()
            val lombanasional = binding.dataLombanasional.text.toString().toFloat()
            val lombaInter = binding.dataLombainter.text.toString().toFloat()
            val magang = binding.dataMagang.text.toString().toFloat()
            val kepanitiaan = binding.dataKepanitiaan.text.toString().toFloat()

            binding.btnSimpan.isEnabled = true

            viewModel.uploadPredictData(IPK, sertif, sertifpro, presnasional, lombanasional, presinter,lombaInter, magang, kepanitiaan )
                .observe(this@DataDiriActivity) { result ->
                    when (result) {
                        is ResultState.Loading -> {
                            binding.probarData.visibility = View.VISIBLE
                        }

                        is ResultState.Success<*> -> {
                            binding.probarData.visibility = View.GONE
                            val response: RegistResponse = result.data as RegistResponse
                            AlertDialog.Builder(this).apply {
                                setTitle("Berhasil!")
                                setMessage(response.message)
                                setPositiveButton("Lanjut") { _, _ ->
                                    val intent = Intent(this@DataDiriActivity, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                create()
                                show()
                            }
                            binding.btnSimpan.isEnabled = true
                        }
                        is ResultState.Error -> {
                            binding.probarData.visibility = View.GONE
                            binding.btnSimpan.isEnabled = true
                    }
                }
        }
    }
}

