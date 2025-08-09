package com.example.rickandmorty.presentation.screens.characterDetail

sealed class CharacterDetailIntent {
    data class LoadCharacterDetail(val characterId: Int, val isRefresh: Boolean): CharacterDetailIntent()
}