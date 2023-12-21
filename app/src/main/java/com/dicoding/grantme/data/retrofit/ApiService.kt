package com.dicoding.grantme.data.retrofit

import com.dicoding.grantme.data.response.LoginResponse
import com.dicoding.grantme.data.response.PredictResponse
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
    @FormUrlEncoded
    @POST("predict")
    suspend fun UpPredictSchoalrship(
        @Field("IPK") ipk: Float,
        @Field("sertifikasi")sertifikasi: Float,
        @Field("sertifikasi_profesional") sertifikasiProfesional: Float,
        @Field("prestasi_nasional") prestasiNasional: Float,
        @Field("lomba_nasional") lombaNasional: Float,
        @Field("prestasi_internasional") prestasiFloaternasional: Float,
        @Field("lomba_internasional") lombaFloaternasional: Float,
        @Field("magang") magang: Float,
        @Field("kepanitiaan") kepanitiaan: Float
   ): PredictResponse

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
//        @Query("page") page: Float = 1,
//        @Query("size") size: Float = 20
//    ): StoryResponse
}