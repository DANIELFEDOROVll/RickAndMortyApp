package com.example.rickandmorty.data.network.entities

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResultDto (
    val info: InfoDto,
    val results: List<CharacterDto>,
    val error: String? = null,
)
