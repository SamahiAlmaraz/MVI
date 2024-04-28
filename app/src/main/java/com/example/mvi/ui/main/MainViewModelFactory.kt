package com.example.mvi.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvi.Api.RestApi
import com.example.mvi.repositorie.MainRepository

class MainViewModelFactory(private val restApi: RestApi) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java))
            return MainActivityViewModel(MainRepository(restApi)) as T

        throw IllegalArgumentException("Unknown class name")
    }
}
