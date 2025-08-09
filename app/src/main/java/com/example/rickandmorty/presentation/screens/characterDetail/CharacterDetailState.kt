package com.example.rickandmorty.presentation.screens.characterDetail

import com.example.rickandmorty.domain.Character

data class CharacterDetailState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val character: Character? = null,
    val isRefreshing: Boolean = false
)
