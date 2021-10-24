package com.example.testapplication

import android.app.Application

class TestApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: TestApplication? = null
        fun getInstance(): TestApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure a classe de Application no AndroidManifest.xml")
            }

            return appInstance!!
        }
    }
}