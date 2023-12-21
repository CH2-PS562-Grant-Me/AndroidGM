package com.dicoding.grantme.ui.detailBeasiswa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.grantme.R
import com.dicoding.grantme.databinding.ActivityDetailBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dibuat = intent.getStringExtra(DIBUAT) ?: ""
        sisa = intent.getStringExtra(SISA) ?: ""
        name = intent.getStringExtra(NAME) ?: ""
        description = intent.getStringExtra(DESCRIPTION) ?: ""
        picture = intent.getStringExtra(PICTURE) ?: ""
        link = intent.getStringExtra(LINK) ?: ""

        val createdAtDate = LocalDate.parse(dibuat, DateTimeFormatter.ISO_DATE_TIME)
        val pendaftaranDate = LocalDate.parse(sisa, DateTimeFormatter.ISO_DATE_TIME)
        val sisaHari = ChronoUnit.DAYS.between(createdAtDate, pendaftaranDate)

        Glide.with(this).load(picture).into(binding.pictDetail)
        binding.namaDetail.text = name
        binding.descDetail.text = description
        binding.sisawaktudetail.text = getString(R.string.sisa_hari_text, sisaHari)
    }

    companion object {
        const val DIBUAT = "DIBUAT"
        const val SISA = "SISA"
        const val NAME = "NAME"
        const val DESCRIPTION = "DESCRIPTION"
        const val PICTURE = "PICTURE"
        const val LINK = "LINK"

        var dibuat: String? = null
        var sisa: String? = null
        var description: String? = null
        var picture: String? = null
        var name: String? = null
        var link: String? = null

    }
}