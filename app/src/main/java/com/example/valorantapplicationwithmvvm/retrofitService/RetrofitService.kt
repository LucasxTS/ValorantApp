package com.example.valorantapplicationwithmvvm.retrofitService

import com.example.valorantapplicationwithmvvm.models.AgentsModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {

     @GET("v1/agents")
     fun getAllData(): Call<AgentsModel>

    companion object {

        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://valorant-api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService {
            return retrofitService
        }
    }
}