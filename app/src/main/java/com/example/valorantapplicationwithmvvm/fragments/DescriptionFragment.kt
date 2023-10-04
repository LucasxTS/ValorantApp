package com.example.valorantapplicationwithmvvm.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.valorantapplicationwithmvvm.R

import com.example.valorantapplicationwithmvvm.databinding.FragmentDescriptionBinding
import com.example.valorantapplicationwithmvvm.glideModule.GlideModule


class DescriptionFragment : Fragment() {

    private val args : DescriptionFragmentArgs by navArgs()
    private lateinit var binding : FragmentDescriptionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingAgents()
    }

    private fun bindingAgents() {
        binding.descriptionAgentName.text = args.agents?.displayName
        binding.descriptionAgentRole.text = args.agents?.role?.displayName

        binding.background.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#${args.agents?.backgroundGradientColors?.first()
            ?.take(6)}"))
        GlideModule.setImage(binding.descriptionAgentImage, requireContext(), args.agents?.fullPortrait)

    }
}