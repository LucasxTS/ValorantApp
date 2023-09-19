package com.example.valorantapplicationwithmvvm

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapplicationwithmvvm.adapters.RecyclerViewAdapter
import com.example.valorantapplicationwithmvvm.databinding.ActivityMainBinding
import com.example.valorantapplicationwithmvvm.models.Agents
import com.example.valorantapplicationwithmvvm.repositories.MainRepository
import com.example.valorantapplicationwithmvvm.retrofitService.RetrofitService
import com.example.valorantapplicationwithmvvm.viewModel.main.MainViewModel
import com.example.valorantapplicationwithmvvm.viewModel.main.MainViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        bindViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllData()
    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(this, {

        })
        recyclerView.adapter = adapter
    }

    private fun bindViewModel() {
        viewModel.liveData.observe(this, Observer { agents ->
            adapter.setData(viewModel.filterAgents(agents.data))
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

    }
}