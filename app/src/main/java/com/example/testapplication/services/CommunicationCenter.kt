package com.example.testapplication.services

object CommunicationCenter {
    const val BASE_URL = "https://api.spacexdata.com/v3/"
    const val COMPANY_INFO = "info"
    const val LAUNCHES = "launches?limit=10"

    fun getRequestUrl(): String? {
        return BASE_URL
    }
}