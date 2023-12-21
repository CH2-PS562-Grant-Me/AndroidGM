package com.dicoding.grantme.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.grantme.R
import com.dicoding.grantme.di.Injection
import com.dicoding.grantme.ui.ViewModelFactory
import com.dicoding.grantme.ui.fragment.ArtikelFragment
import com.dicoding.grantme.ui.fragment.BeasiswaFragment
import com.dicoding.grantme.ui.fragment.BerandaFragment
import com.dicoding.grantme.ui.fragment.ProfilFragment
import com.dicoding.grantme.ui.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private var selectedTab = 1
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val berandaPage = findViewById<LinearLayout>(R.id.page_home)
        val beasiswaPage = findViewById<LinearLayout>(R.id.page_beasiswa)
        val artikelPage = findViewById<LinearLayout>(R.id.page_status)
        val profilPage = findViewById<LinearLayout>(R.id.page_profile)
        val homeicon = findViewById<ImageView>(R.id.icon_home)
        val beasiswaicon = findViewById<ImageView>(R.id.icon_beasiswa)
        val statusicon = findViewById<ImageView>(R.id.icon_status)
        val profileicon = findViewById<ImageView>(R.id.icon_profil)
        val texthome = findViewById<TextView>(R.id.text_home)
        val textbeasiswa = findViewById<TextView>(R.id.text_beasiswa)
        val textstatus = findViewById<TextView>(R.id.text_status)
        val textprofil = findViewById<TextView>(R.id.text_profile)

        supportFragmentManager
            .beginTransaction().setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, BerandaFragment::class.java, null)
            .commit()
        berandaPage.setOnClickListener {
            if (selectedTab != 1) {
                supportFragmentManager
                    .beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, BerandaFragment::class.java, null)
                    .commit()
                textbeasiswa.visibility = View.GONE
                textprofil.visibility = View.GONE
                textstatus.visibility = View.GONE
                beasiswaicon.setImageResource(R.drawable.icon_beasiswa)
                statusicon.setImageResource(R.drawable.icon_status)
                profileicon.setImageResource(R.drawable.icon_profile)
                beasiswaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                artikelPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                profilPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                texthome.visibility = View.VISIBLE
                homeicon.setImageResource(R.drawable.icon_selected_home)
                berandaPage.setBackgroundResource(R.drawable.round_icon)
                val scaleAnimation =
                    ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF.toFloat(), 0.0f)
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                berandaPage.startAnimation(scaleAnimation)
                selectedTab = 1
            }
        }
        beasiswaPage.setOnClickListener {
            if (selectedTab != 2) {
                supportFragmentManager
                    .beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, BeasiswaFragment::class.java, null)
                    .commit()
                texthome.visibility = View.GONE
                textstatus.visibility = View.GONE
                textprofil.visibility = View.GONE
                homeicon.setImageResource(R.drawable.icon_home)
                statusicon.setImageResource(R.drawable.icon_status)
                profileicon.setImageResource(R.drawable.icon_profile)
                berandaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                artikelPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                profilPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                textbeasiswa.visibility = View.VISIBLE
                beasiswaicon.setImageResource(R.drawable.icon_selected_beasiswa)
                beasiswaPage.setBackgroundResource(R.drawable.round_icon)
                val scaleAnimation =
                    ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF.toFloat(), 0.0f)
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                beasiswaPage.startAnimation(scaleAnimation)
                selectedTab = 2
            }
        }
        artikelPage.setOnClickListener {
            if (selectedTab != 3) {
                supportFragmentManager
                    .beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, ArtikelFragment::class.java, null)
                    .commit()
                texthome.visibility = View.GONE
                textbeasiswa.visibility = View.GONE
                textprofil.visibility = View.GONE
                homeicon.setImageResource(R.drawable.icon_home)
                beasiswaicon.setImageResource(R.drawable.icon_beasiswa)
                profileicon.setImageResource(R.drawable.icon_profile)
                berandaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                beasiswaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                profilPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                textstatus.visibility = View.VISIBLE
                statusicon.setImageResource(R.drawable.icon_selected_status)
                artikelPage.setBackgroundResource(R.drawable.round_icon)
                val scaleAnimation =
                    ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF.toFloat(), 0.0f)
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                artikelPage.startAnimation(scaleAnimation)
                selectedTab = 3
            }
        }
        profilPage.setOnClickListener {
            if (selectedTab != 4) {
                supportFragmentManager
                    .beginTransaction().setReorderingAllowed(true)
                    .replace(R.id.fragmentContainer, ProfilFragment::class.java, null)
                    .commit()
                textbeasiswa.visibility = View.GONE
                texthome.visibility = View.GONE
                textstatus.visibility = View.GONE
                beasiswaicon.setImageResource(R.drawable.icon_beasiswa)
                statusicon.setImageResource(R.drawable.icon_status)
                homeicon.setImageResource(R.drawable.icon_home)
                beasiswaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                artikelPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                berandaPage.setBackgroundColor(resources.getColor(android.R.color.transparent))
                textprofil.visibility = View.VISIBLE
                profileicon.setImageResource(R.drawable.icon_selected_profile)
                profilPage.setBackgroundResource(R.drawable.round_icon)
                val scaleAnimation =
                    ScaleAnimation(0.8f, 1.0f, 1f, 1f, Animation.RELATIVE_TO_SELF.toFloat(), 0.0f)
                scaleAnimation.duration = 200
                scaleAnimation.fillAfter = true
                profilPage.startAnimation(scaleAnimation)
                selectedTab = 4
            }
        }
        val userRepository = Injection.provideRepository(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[MainViewModel::class.java]
        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }
    }
}