package com.example.valorantapplicationwithmvvm.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.valorantapplicationwithmvvm.databinding.AbilitiesLayoutBinding
import com.example.valorantapplicationwithmvvm.glideModule.GlideModule
import com.example.valorantapplicationwithmvvm.models.Agents

class SkillListAdapter(private val context: Context, private val agent: Agents?) : RecyclerView.Adapter<SkillListAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: AbilitiesLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        val skillName = binding.skillName
        val skillDescription = binding.skillDescription
        val skillImage = binding.skillImage

        fun binding(agent: Agents?, position: Int) {
            if (agent != null) {
                skillName.text = agent.abilities[position].displayName
                val url = agent.abilities[position].displayIcon
                GlideModule.setImage(skillImage, context, url)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AbilitiesLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val agent = agent
        holder.binding(agent, position)
    }

    override fun getItemCount() = 4


}