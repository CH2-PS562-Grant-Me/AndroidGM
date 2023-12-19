
package com.dicoding.grantme.ui.login
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.grantme.data.UserRepository
import com.dicoding.grantme.data.pref.UserModel
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    val errorLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val hasil = repository.login(email, password)
                if (hasil.error == false) {
                    saveSession(UserModel(email, hasil.data!!.accessToken!!, true))
                } else {
                    errorLiveData.postValue("Login failed: ${hasil.message}")
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    when (e.code()) {
                        401 -> errorLiveData.postValue("Pengguna tidak ditemukan")
                        else -> errorLiveData.postValue("An error occurred: ${e.message()}")
                    }
                } else {
                    errorLiveData.postValue("An error occurred: ${e.message}")
                }
            }
        }
    }
    private fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
}
