package com.example.valorantapplicationwithmvvm.viewModel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valorantapplicationwithmvvm.models.Agents
import com.example.valorantapplicationwithmvvm.models.AgentsModel
import com.example.valorantapplicationwithmvvm.repositories.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val liveData = MutableLiveData<AgentsModel>()
    val errorMessage = MutableLiveData<String>()

    fun getAllData() {
        val request = repository.getAllData()
        request.enqueue(object : Callback<AgentsModel> {
            override fun onResponse(
                call: Call<AgentsModel>,
                response: Response<AgentsModel>
            ) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<AgentsModel>, t: Throwable) {
                errorMessage.postValue(t.message)
                println(t.message)
            }

        })
    }

     fun filterAgents(agents: List<Agents>): List<Agents> {
        return agents.filter { it.displayName != null && it.role?.displayName != null }
    }
}