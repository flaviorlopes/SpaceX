package com.example.testapplication.services

import android.os.Build

object HeaderInfo {
    const val AUTHORIZATION = "Authorization"
    const val USER_AGENT = "User-Agent"
    const val APP_DEVICE_HEADER = "X-APP-Device"
    const val APP_LANGUAGE_HEADER = "X-APP-Language"
    const val APP_VERSION_HEADER = "X-APP-Version"
    const val APP_BUNDLE_VERSION_HEADER = "X-APP-BUNDLE-ID"
    const val CONTENT_TYPE = "Content-Type"
    const val CONNECTION = "Connection"
    const val COOKIE = "Cookie"

    const val TABLET_DEVICE_CODE = "tab"
    const val SMARTPHONE_DEVICE_CODE = "cel"

    const val APP_VERSION = "5.0"
    const val APP_LANGUAGE = "pt"
    const val CONTENT_TYPE_JSON = "application/json; charset=utf-8"
    const val CONNECTION_CLOSE = "Close"


    /**
     * @return The user agent string to be included on HTTP request header.
     */
    fun getUserAgent(): String? {
        return Build.MANUFACTURER + ";" +
                Build.MODEL + ";" +
                "Android" + ";" +
                Build.VERSION.RELEASE + ";" +
                APP_VERSION
    }
}