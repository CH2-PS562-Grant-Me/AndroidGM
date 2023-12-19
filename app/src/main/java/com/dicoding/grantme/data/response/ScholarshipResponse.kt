package com.dicoding.grantme.data.response

import com.google.gson.annotations.SerializedName

data class ScholarshipResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("tanggal_pendaftaran")
	val tanggalPendaftaran: String? = null,

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("img_url")
	val imgUrl: String? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("jenis_beasiswa")
	val jenisBeasiswa: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
