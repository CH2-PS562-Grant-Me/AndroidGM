package com.dicoding.grantme.di

import android.content.Context
import com.dicoding.grantme.data.UserRepository
import com.dicoding.grantme.data.pref.UserPreference
import com.dicoding.grantme.data.pref.dataStore
import com.dicoding.grantme.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(apiService, pref)
    }
}