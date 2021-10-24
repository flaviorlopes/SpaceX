package com.example.testapplication.services

import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitRequest {
    @GET(CommunicationCenter.LAUNCHES)
    suspend fun getLaunches(): List<Launch>


    @GET(CommunicationCenter.LAUNCHES)
    suspend fun getLaunchesFiltered(
        @Query("launch_year") launch_year: Int,
        @Query("order") order: String,
        @Query("launch_success") launch_success: Boolean?
    ): List<Launch>

    @GET(CommunicationCenter.COMPANY_INFO)
    suspend fun getInfo(): CompanyInfo
}