package com.example.rickandmorty.presentation.screens.home

import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import com.example.rickandmorty.domain.CharactersResult


data class HomeState(
    val isLoading: Boolean = true,
    val isLoadingNextPage: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false,
    val characters: CharactersResult = CharactersResult(emptyList()),
    val searchValue: String = "",
    val currentPage: Int = 1,

    val status: String = "",
    val species: String = "",
    val type: String = "",
    val gender: String = ""
)
