package com.example.testapplication.retrofit

import com.example.testapplication.model.PlacesResponse
import retrofit2.http.GET

interface RetrofitServices {
    @GET("places")
    suspend fun loadPlaces(): PlacesResponse
}