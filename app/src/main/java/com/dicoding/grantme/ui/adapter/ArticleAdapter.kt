package com.dicoding.grantme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.grantme.data.response.ArticleItem
import com.dicoding.grantme.databinding.GridArtikelBinding


class ArticleAdapter(private val onClick: (ArticleItem) -> Unit) :
    ListAdapter<ArticleItem, ArticleAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = GridArtikelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data, onClick)
    }

    class MyViewHolder(private val binding: GridArtikelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(art: ArticleItem, onClick: (ArticleItem) -> Unit) {
            with(binding) {

                namabeaArtikel.text = art.beasiswa
                namaPenerimaArt.text = art.namaPenerima
                univArt.text = art.universitas
                ulasanArt.text = art.ulasan
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticleItem>() {
            override fun areItemsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ArticleItem, newItem: ArticleItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
