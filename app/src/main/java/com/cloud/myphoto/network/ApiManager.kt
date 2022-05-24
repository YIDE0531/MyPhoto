package com.cloud.myphoto.network

import com.cloud.myphoto.BuildConfig.APP_API_HOST
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ApiManager {
    private val retrofit: Retrofit
    private val okHttpClient = OkHttpClient()

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(APP_API_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    companion object {
        private val manager = ApiManager()
        val client: Retrofit
            get() = manager.retrofit
    }
}