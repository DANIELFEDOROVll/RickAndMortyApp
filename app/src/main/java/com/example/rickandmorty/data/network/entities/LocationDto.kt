package com.example.rickandmorty.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    val name: String,
    val url: String
)
