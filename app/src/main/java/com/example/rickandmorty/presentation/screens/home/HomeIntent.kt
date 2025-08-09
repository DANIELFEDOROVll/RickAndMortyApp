package com.example.rickandmorty.presentation.screens.home

sealed class HomeIntent {
    data object LoadStartPage: HomeIntent()
    data object LoadNextPage: HomeIntent()
    data class NewSearchValue(val value: String): HomeIntent()
    data class ChangeFilter(
        val status: String? = null,
        val species: String? = null,
        val type: String? = null,
        val gender: String? = null,
    ): HomeIntent()
}