package com.dicoding.grantme.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.grantme.data.pref.UserModel
import com.dicoding.grantme.data.pref.UserPreference
import com.dicoding.grantme.data.response.LoginResponse
import com.dicoding.grantme.data.response.PredictResponse
import com.dicoding.grantme.data.response.RegistResponse
import com.dicoding.grantme.data.response.ScholarshipResponse
import com.dicoding.grantme.data.retrofit.ApiService
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException


class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    private val _successMessage = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> = _errorLiveData

    suspend fun saveSession(user: UserModel) {
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return userPreference.getSession()
    }

    suspend fun login(email: String, password: String): LoginResponse {
        _isLoading.value = true
        return apiService.login(email, password)
    }

    suspend fun getallScholarship(): ScholarshipResponse {
        return apiService.getAllScholarship() //"Bearer $token")
    }

    suspend fun logout() {
        userPreference.logout()
    }
    suspend fun upPredict(ipk: Float, sertifikasi: Float,
                          sertifikasiProfesional: Float,
                          prestasiNasional: Float,
                          lombaNasional: Float,
                          prestasiInternasional: Float,
                          lombaInternasional: Float,
                          magang: Float,
                          kepanitiaan: Float
    ): PredictResponse{
        return apiService.UpPredictSchoalrship(ipk,
            sertifikasi,
            sertifikasiProfesional,
            prestasiNasional,
            lombaNasional,
            prestasiInternasional,
            lombaInternasional,
            magang,
            kepanitiaan
        )
    }

//    suspend fun uploadImage(
//        token: String,
//        imageFile: File,
//        description: String,
//        longitude: Double,
//        latitude: Double
//    ): AddStoryResponse {
//        val requestBody = description.toRequestBody("EditText/TextView".toMediaType())
//        val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
//        val lat = latitude.toString().toRequestBody("text/plain".toMediaType())
//        val lon = longitude.toString().toRequestBody("text/plain".toMediaType())
//        val multipartBody = MultipartBody.Part.createFormData(
//            "photo",
//            imageFile.name,
//            requestImageFile
//        )
//
//        return apiService.uploadImage("Bearer $token", multipartBody, requestBody, lat, lon)
//    }

    suspend fun register(
        nama: String,
        email: String,
        password: String
    ): ResultState<RegistResponse> {
        return try {
            val response = apiService.register(nama, email, password)
            ResultState.Success(response)
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, RegistResponse::class.java)
            val errorMessage = errorBody.message
            ResultState.Error(errorMessage)
        }
    }

//    suspend fun getStoryWithLocation(token: String, location: Int = 1): StoryResponse {
//        return apiService.getStoriesWithLocation("Bearer $token", location)
//    }
//
//    fun getQuote(token: String): LiveData<PagingData<ListStoryItem>> {
//        return Pager(config = PagingConfig(pageSize = 5), pagingSourceFactory = {
//            Paging(
//                apiService, "Bearer $token"
//            )
//        }
//        ).liveData
//    }
//
//    suspend fun getAllStory(token: String, page: Int = 1, size: Int = 20): StoryResponse {
//        return apiService.getAllStory("Bearer $token", page, size)
//    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}