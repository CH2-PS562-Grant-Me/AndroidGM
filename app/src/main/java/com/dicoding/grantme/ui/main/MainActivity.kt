package com.dicoding.grantme.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.grantme.R
import com.dicoding.grantme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}