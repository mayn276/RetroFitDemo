package com.example.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    companion object{
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        val interceptor = HttpLoggingInterceptor().apply{
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(20,TimeUnit.SECONDS)
                .readTimeout(25,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
        }.build()
        fun getRetroFitInstance(): Retrofit {
         return Retrofit.Builder()
             .baseUrl(BASE_URL)
             .client(client)
             .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())).build()

        }

    }
}