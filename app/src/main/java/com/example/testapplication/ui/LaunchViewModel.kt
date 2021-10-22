package com.example.testapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testapplication.models.CompanyInfo
import com.example.testapplication.models.Launch
import com.example.testapplication.services.RetrofitHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class LaunchViewModel : ViewModel() {
    var isReady: MutableLiveData<Boolean> = MutableLiveData()

    val launches: MutableLiveData<List<Launch>> by lazy {
        MutableLiveData<List<Launch>>().also {
            loadLaunches()
        }
    }


    val companyInfo: MutableLiveData<CompanyInfo> by lazy {
        MutableLiveData<CompanyInfo>().also {
            loadCompanyInfo()
        }
    }

    fun getLaunches(): LiveData<List<Launch>> {
        return launches
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

    private fun loadLaunches() {
        viewModelScope.launch {
            val ls = mutableListOf<Launch>()
            ls.addAll(RetrofitHelper.retrofit.getLaunches())
            launches.postValue(ls)
        }
    }

}
