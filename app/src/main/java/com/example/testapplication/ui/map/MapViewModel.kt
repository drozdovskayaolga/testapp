package com.example.testapplication.ui.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.model.Places
import com.example.testapplication.retrofit.RetrofitClient
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {
    private val retrofitService = RetrofitClient.retrofitService

    private val _places = MutableLiveData<List<Places>>()
    val places: LiveData<List<Places>> get() = _places

    fun loadPlacesList() = viewModelScope.launch {
        try {
            _places.value = retrofitService.loadPlaces().places
        } catch (exception: Exception) {
            Log.e("RETROFIT ERROR", "error", exception)
        }
    }
}