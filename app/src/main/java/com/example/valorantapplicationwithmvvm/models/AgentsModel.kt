package com.example.valorantapplicationwithmvvm.models

data class AgentsModel(
    val data: List<Agents>
)

data class Agents(
    val displayName: String?,
    val fullPortrait: String?,
    val role: Role?,
    val backgroundGradientColors: List<String>
)

data class Role(
    val displayName: String?
)
