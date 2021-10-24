package com.example.testapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch
import com.example.testapplication.models.requests.FilterLaunches
import com.example.testapplication.services.RetrofitHelper
import kotlinx.coroutines.launch


class LaunchViewModel : ViewModel() {
    var filter: MutableLiveData<FilterLaunches> = MutableLiveData()
    var launches: MutableLiveData<List<Launch>> = MutableLiveData()

    val companyInfo: MutableLiveData<CompanyInfo> by lazy {
        MutableLiveData<CompanyInfo>().also {
            loadCompanyInfo()
        }
    }

    fun getInfo(): LiveData<CompanyInfo> {
        return companyInfo
    }

    private fun loadCompanyInfo() {
        viewModelScope.launch {
            val ci : CompanyInfo = RetrofitHelper.retrofit.getInfo()
            companyInfo.postValue(ci)
        }
    }

    fun loadLaunches() {
        viewModelScope.launch {
            val ls = mutableListOf<Launch>()
            if (filter.value?.year != null && filter.value?.order != null) {
                val success = if (filter.value!!.success) true else null
                ls.addAll(RetrofitHelper.retrofit.getLaunchesFiltered(filter.value!!.year, filter.value!!.order, success))
            } else {
                ls.addAll(RetrofitHelper.retrofit.getLaunches())
            }
            launches.postValue(ls)
        }
    }



}
