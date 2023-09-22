package com.example.valorantapplicationwithmvvm.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapplicationwithmvvm.R
import com.example.valorantapplicationwithmvvm.adapters.RecyclerViewAdapter
import com.example.valorantapplicationwithmvvm.databinding.FirstFragmnetBinding
import com.example.valorantapplicationwithmvvm.interfaces.OnItemClickListener
import com.example.valorantapplicationwithmvvm.repositories.MainRepository
import com.example.valorantapplicationwithmvvm.retrofitService.RetrofitService
import com.example.valorantapplicationwithmvvm.viewModel.main.MainViewModel
import com.example.valorantapplicationwithmvvm.viewModel.main.MainViewModelFactory

class FirstFragment() : Fragment(R.layout.first_fragmnet), OnItemClickListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: RecyclerViewAdapter
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var binding : FirstFragmnetBinding
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        bindViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstFragmnetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FirstFragmnetBinding.bind(view)
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

    private fun bindViewModel() {
        viewModel.liveData.observe(this, Observer { agents ->
            adapter.setData(viewModel.filterAgents(agents.data))
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            println("An error occurred")
        })

    }

    private fun setupRecyclerView() {
        recyclerView = binding.recyclerView
        val layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerViewAdapter(requireContext(), this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(position: Int) {
        val agents = viewModel.liveData.value?.data
        val agentClicked = agents?.get(position)
        val action = FirstFragmentDirections.fromFirstFragmentToSecondFragment(agentClicked)
     findNavController().navigate(action)
    }
}