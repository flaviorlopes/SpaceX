package com.example.testapplication.services

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RetrofitCallback<T>: Callback<T> {
    override fun onResponse(call: Call<T>, response: Response<T>) {

    }

    override fun onFailure(call: Call<T>, t: Throwable) {

    }
}