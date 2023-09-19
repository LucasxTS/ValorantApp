package com.example.valorantapplicationwithmvvm.repositories

import com.example.valorantapplicationwithmvvm.retrofitService.RetrofitService

class MainRepository(private val retrofitService: RetrofitService) {

    fun getAllData() = retrofitService.getAllData()
}