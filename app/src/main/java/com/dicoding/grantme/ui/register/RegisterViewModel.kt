package com.dicoding.grantme.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.grantme.data.UserRepository
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    fun register(nama: String, email: String, password: String): LiveData<*> {
        val responseLiveData = MutableLiveData<Any>()

        viewModelScope.launch {
            try {
                val response = repository.register(nama, email, password)
                responseLiveData.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return responseLiveData
    }
}