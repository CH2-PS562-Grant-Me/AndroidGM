package com.dicoding.grantme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.grantme.R
import com.dicoding.grantme.data.response.DataPredict
import com.dicoding.grantme.databinding.GridMainBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class RecomAdapter(private val onClick: (DataPredict) -> Unit) : ListAdapter<DataPredict, RecomAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =GridMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onClick)
    }

    class MyViewHolder(private val binding: GridMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(predict: DataPredict, onClick: (DataPredict) -> Unit) {
            with(binding) {
                Glide.with(itemView).load(predict.imgUrl).into(beaPict)
                val createdAtDate = LocalDate.parse(predict.createdAt, DateTimeFormatter.RFC_1123_DATE_TIME)
                val pendaftaranDate = LocalDate.parse(predict.tanggalPendaftaran, DateTimeFormatter.RFC_1123_DATE_TIME)
                val sisaHari = ChronoUnit.DAYS.between(createdAtDate, pendaftaranDate)

                namabeagrid.text = predict.nama
                sisawaktugrid.text = itemView.context.getString(R.string.sisa_hari_text, sisaHari)
            }
            itemView.setOnClickListener { onClick(predict) }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataPredict>() {
            override fun areItemsTheSame(oldItem: DataPredict, newItem: DataPredict): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: DataPredict, newItem: DataPredict): Boolean {
                return oldItem == newItem
            }
        }
    }
}