package com.example.testapplication.services

import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


object RetrofitHelper {
    /*
    fun buildRequest(url: String?): RetrofitRequest? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val builder: Request.Builder = chain.request().newBuilder()
                    .addHeader(HeaderInfo.USER_AGENT, getUserAgent()!!)
                    .addHeader(HeaderInfo.APP_LANGUAGE_HEADER, HeaderInfo.APP_LANGUAGE)
                    .addHeader(HeaderInfo.APP_VERSION_HEADER, HeaderInfo.APP_VERSION)
                if (!ApplicationGlobals.sessionCookie1.isEmpty() && !ApplicationGlobals.sessionCookie2.isEmpty()
                ) {
                    builder.addHeader(HeaderInfo.COOKIE, ApplicationGlobals.sessionCookie1)
                        .addHeader(HeaderInfo.COOKIE, ApplicationGlobals.sessionCookie2)
                }
                chain.proceed(builder.build())
            })
            .addInterceptor(logging)
            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val request: Request = chain.request()
                val response: Response = chain.proceed(request)
                if (response.code == 403) {
                    // Sessão Expirada
                } else if (response.code == 302 || response.code == 200 && response.body != null && response.body!!
                        .contentType().toString() == "text/html"
                ) {
                    // Servidor indisponível
                }
                response
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit.create(IRetrofitRequest::class.java)
       } */

        val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(CommunicationCenter.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RetrofitRequest::class.java)
        }
}