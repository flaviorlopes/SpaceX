package com.example.testapplication.services

import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch
import retrofit2.http.GET


interface RetrofitRequest {
    @GET(CommunicationCenter.LAUNCHES)
    suspend fun getLaunches(): List<Launch>

    @GET(CommunicationCenter.COMPANY_INFO)
    suspend fun getInfo(): CompanyInfo
}