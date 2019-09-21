package com.ydhnwb.workshopmvpkotlin.webservices

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {
    companion object {
        private const val ENDPOINT = "https://jsonplaceholder.typicode.com/"
        private var retrofit : Retrofit? = null
        private var options = OkHttpClient.Builder().apply {
            writeTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            connectTimeout(30, TimeUnit.SECONDS)
        }.build()

        private fun getClient() : Retrofit {
            return if(retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(ENDPOINT)
                    .client(options)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun instance(): ApiService = getClient().create(ApiService::class.java)
    }
}