package com.example.valorantapplicationwithmvvm.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.valorantapplicationwithmvvm.R
import com.example.valorantapplicationwithmvvm.databinding.AgentsLayoutBinding
import com.example.valorantapplicationwithmvvm.models.Agents
import com.example.valorantapplicationwithmvvm.models.AgentsModel

class RecyclerViewAdapter(private val context: Context, private val onItemClicked : (Agents) -> Unit) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var agentsList = mutableListOf<Agents>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Agents>) {
        this.agentsList = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(binding: AgentsLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        val agentName = binding.agentName
        val agentRole = binding.agentRole
        val agentImage = binding.agentImage
        val background = binding.background

        fun bind(agents: Agents) {
            agentName.text = agents.displayName
            agentRole.text = agents.role?.displayName

            background.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#${agents.backgroundGradientColors.first().take(6)}"))

            val url = agents.fullPortrait

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(url)
                .into(agentImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AgentsLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agents = agentsList[position]
        holder.bind(agents)
    }


    override fun getItemCount() = agentsList.size

}