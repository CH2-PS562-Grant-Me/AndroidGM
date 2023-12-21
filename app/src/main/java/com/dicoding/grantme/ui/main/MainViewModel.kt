package com.dicoding.grantme.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.grantme.data.UserRepository
import com.dicoding.grantme.data.pref.UserModel
import com.dicoding.grantme.data.response.PredictResponse
import com.dicoding.grantme.data.response.ScholarshipResponse
import kotlinx.coroutines.launch
import java.io.File

class MainViewModel(private val repository: UserRepository) : ViewModel() {
    private val _storyResponse = MutableLiveData<ScholarshipResponse>()
    val storyResponse: LiveData<ScholarshipResponse> = _storyResponse

    private val _predict = MutableLiveData<PredictResponse>()
    val preResponse: LiveData<PredictResponse> = _predict

    private val _predictResponse = MutableLiveData<PredictResponse>()
    val addStoryResponse: LiveData<PredictResponse> = _predictResponse

    //    private val _locationResponse = MutableLiveData<StoryResponse>()
    //    val locationResponse: LiveData<StoryResponse> = _locationResponse

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getAllScholarship() {
        viewModelScope.launch {
            try {
                val response = repository.getallScholarship()
                _storyResponse.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun uploadPredictData(
        ipk: Float,
        sertifikasi: Float,
        sertifikasiProfesional: Float,
        prestasiNasional: Float,
        lombaNasional: Float,
        prestasiInternasional: Float,
        lombaInternasional: Float,
        magang: Float,
        kepanitiaan: Float
    ): LiveData<*> {
        val responseLiveData = MutableLiveData<PredictResponse>()
        viewModelScope.launch {
            try {
                // Call the repository function to upload data
                val response = repository.upPredict(
                    ipk,
                    sertifikasi,
                    sertifikasiProfesional,
                    prestasiNasional,
                    lombaNasional,
                    prestasiInternasional,
                    lombaInternasional,
                    magang,
                    kepanitiaan
                )
                _predict.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
        return _predict
    }
//    fun logout() {
//        viewModelScope.launch {
//            repository.logout()
//        }
//    }
//    fun getStoriesWithLocation(token: String, location: Int = 1) {
//        viewModelScope.launch {
//            _locationResponse.value = repository.getStoryWithLocation(token,location)
//        }
//    }
//    fun getAllStory(token: String, page: Int = 1, size: Int = 20) {
//        viewModelScope.launch {
//            repository.getAllStory(token, page, size)
//        }
//    }
//    fun getData(token: String) : LiveData<PagingData<ListStoryItem>> {
//        return repository.getQuote(token)
//    }
//    fun uploadImage(token : String, file: File, description: String, longitude: Double, latitude: Double) {
//        viewModelScope.launch {
//            _addStoryResponse.value = repository.uploadImage(token, file, description, longitude, latitude)
//        }
//    }


}