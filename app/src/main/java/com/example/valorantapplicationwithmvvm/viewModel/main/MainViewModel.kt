package com.example.valorantapplicationwithmvvm.viewModel.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.valorantapplicationwithmvvm.models.Agents
import com.example.valorantapplicationwithmvvm.models.AgentsModel
import com.example.valorantapplicationwithmvvm.repositories.MainRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import retrofit2.awaitResponse

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    val liveData = MutableLiveData<AgentsModel>()
    val errorMessage = MutableLiveData<String>()

    @OptIn(DelicateCoroutinesApi::class)
    fun getAllData() {
        GlobalScope.launch(Dispatchers.IO) {
            val request = repository.getAllData().awaitResponse()
            if (request.isSuccessful) {
                liveData.postValue(request.body())
                println(request.body())
            } else {
                errorMessage.postValue(request.toString())
            }

        }
    }

     fun filterAgents(agents: List<Agents>): List<Agents> {
        return agents.filter { it.isPlayableCharacter }
    }
}