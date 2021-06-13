package com.example.testapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://2fjd9l3x1l.api.quickmocker.com/kyiv/"
    val retrofitService: RetrofitServices = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitServices::class.java)
}