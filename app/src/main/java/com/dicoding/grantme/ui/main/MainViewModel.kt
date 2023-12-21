package com.dicoding.grantme.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.grantme.data.UserRepository
import com.dicoding.grantme.data.pref.UserModel
import com.dicoding.grantme.data.response.ArticleResponse
import com.dicoding.grantme.data.response.DataItem
import com.dicoding.grantme.data.response.PredictResponse
import com.dicoding.grantme.data.response.ScholarshipResponse
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository) : ViewModel() {

    private val _scholarships = MutableLiveData<ScholarshipResponse>()
    val scholarships: LiveData<ScholarshipResponse> get() = _scholarships

    private val _article = MutableLiveData<ArticleResponse>()
    val article: LiveData<ArticleResponse> get() = _article

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _predict = MutableLiveData<PredictResponse>()
    val preResponse: LiveData<PredictResponse> = _predict


    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getAllScholarship(jenisBeasiswa: String) {
        viewModelScope.launch {
            try {
                val response = repository.getFilteredScholarships(jenisBeasiswa)
                _scholarships.value = response
            } catch (e: Exception) {
                _error.value = "Error fetching scholarships: ${e.message}"
            }
        }
    }

    fun getArticle() {
        viewModelScope.launch {
            try {
                val response = repository.getAllarticle()
                _article.value = response
            } catch (e: Exception) {
                _error.value = "Error fetching scholarships: ${e.message}"
            }
        }
    }

    fun uploadPredictData(
        IPK: Float,
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
                val response = repository.upPredict(
                    IPK,
                    sertifikasi,
                    sertifikasiProfesional,
                    prestasiNasional,
                    lombaNasional,
                    prestasiInternasional,
                    lombaInternasional,
                    magang,
                    kepanitiaan
                )
                responseLiveData.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()

            }
        }
        return responseLiveData
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