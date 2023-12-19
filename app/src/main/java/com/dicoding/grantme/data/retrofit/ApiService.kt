package com.dicoding.grantme.data.retrofit

import com.dicoding.grantme.data.response.LoginResponse
import com.dicoding.grantme.data.response.RegistResponse
import com.dicoding.grantme.data.response.ScholarshipResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
//import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun register(
        @Field("nama") nama: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegistResponse

//    @GET("stories")
//    suspend fun getStoriesWithLocation(
//        @Header("Authorization") token : String,
//        @Query("location") location : Int = 1,
//    ): StoryResponse
    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @GET("scholarships")
    suspend fun getAllScholarship(
        // @Header("Authorization") token: String,
    ): ScholarshipResponse


//    @Multipart
//    @POST("stories")
//    suspend fun uploadImage(
//        @Header("Authorization") token : String,
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//        @Part("lon") longitude: RequestBody,
//        @Part("lat") latitude: RequestBody
//    ): AddStoryResponse
//    @GET("stories")
//    suspend fun getAllStory(
//        @Header("Authorization") token: String,
//        @Query("page") page: Int = 1,
//        @Query("size") size: Int = 20
//    ): StoryResponse
}