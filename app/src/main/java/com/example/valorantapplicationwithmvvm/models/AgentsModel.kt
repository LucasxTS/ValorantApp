package com.example.valorantapplicationwithmvvm.models

import java.io.Serializable

data class AgentsModel(
    val data: List<Agents>
) : Serializable

data class Agents(
    val description: String,
    val displayName: String?,
    val fullPortrait: String?,
    val role: Role?,
    val backgroundGradientColors: List<String>,
    val isPlayableCharacter: Boolean,
    val abilities: List<abilities>
) : Serializable

data class Role(
    val displayName: String?
) : Serializable

data class abilities(
    val displayName: String,
    val description: String,
    val displayIcon: String
) : Serializable