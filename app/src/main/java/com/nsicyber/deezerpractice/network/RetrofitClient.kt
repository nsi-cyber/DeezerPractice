package com.nsicyber.deezerpractice.network

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    var retrofit: Retrofit? = null
    var CONNECTION_TIMEOUT = 60

    val baseUrl: String
        get() {

            var BASE_URL = "https://api.deezer.com"

            return BASE_URL
        }

    fun retrofitInterface(context: Context?): RetrofitInterface {
        return getApiClient(context).create(RetrofitInterface::class.java)
    }

    fun getApiClient(context: Context?): Retrofit {

        val httpClient = OkHttpClient.Builder()
            .readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!


    }
}
