package com.example.valorantapplicationwithmvvm.models

import java.io.Serializable

data class AgentsModel(
    val data: List<Agents>
) : Serializable

data class Agents(
    val displayName: String?,
    val fullPortrait: String?,
    val role: Role?,
    val backgroundGradientColors: List<String>,
    val isPlayableCharacter: Boolean
) : Serializable

data class Role(
    val displayName: String?
) : Serializable
