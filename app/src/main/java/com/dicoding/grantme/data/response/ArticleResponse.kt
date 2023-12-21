package com.dicoding.grantme.data.response

import com.google.gson.annotations.SerializedName

data class ArticleResponse(

	@field:SerializedName("data")
	val data: List<ArticleItem?>? = null
)

data class ArticleItem(

	@field:SerializedName("ulasan")
	val ulasan: String? = null,

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("tahun")
	val tahun: String? = null,

	@field:SerializedName("sumber")
	val sumber: String? = null,

	@field:SerializedName("nama_penerima")
	val namaPenerima: String? = null,

	@field:SerializedName("universitas")
	val universitas: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("beasiswa")
	val beasiswa: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
