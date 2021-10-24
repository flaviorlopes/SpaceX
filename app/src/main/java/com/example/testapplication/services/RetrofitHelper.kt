package com.example.testapplication.services

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


object RetrofitHelper {
        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(CommunicationCenter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitRequest::class.java)
        }
}