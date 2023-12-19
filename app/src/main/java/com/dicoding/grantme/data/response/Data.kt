package com.dicoding.grantme.data.response

import com.google.gson.annotations.SerializedName

data class DataLogin(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("accessToken")
    val accessToken: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
