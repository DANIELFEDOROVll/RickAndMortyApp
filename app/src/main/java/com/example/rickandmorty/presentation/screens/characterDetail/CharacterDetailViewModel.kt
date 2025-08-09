package com.example.rickandmorty.presentation.screens.characterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel(
    characterId: Int,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(CharacterDetailState())
    val state: StateFlow<CharacterDetailState> get() = _state

    init {
        handleIntent(CharacterDetailIntent.LoadCharacterDetail(characterId, false))
    }

    fun handleIntent(intent: CharacterDetailIntent){
        when(intent){
            is CharacterDetailIntent.LoadCharacterDetail -> loadCharacterDetail(intent.characterId, intent.isRefresh)
        }
    }

    private fun loadCharacterDetail(characterId: Int, isRefresh: Boolean) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = !isRefresh,
                isRefreshing = isRefresh
            )

            val response = getCharacterByIdUseCase(characterId)

            response.onSuccess { character ->
                _state.value = _state.value.copy(
                    character = character,
                    isLoading = false,
                    isRefreshing = false,
                    error = null
                )
            }
            response.onFailure { error ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    isRefreshing = false,
                    error = error.message
                )
            }
        }
    }
}