package com.example.rickandmorty.presentation.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.domain.CharactersResult
import com.example.rickandmorty.domain.usecases.SearchCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class HomeViewModel(
    private val searchCharactersUseCase: SearchCharactersUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> get() = _state

    init {
        handleIntent(HomeIntent.LoadStartPage)
    }

    fun handleIntent(intent: HomeIntent){
        when(intent){
            is HomeIntent.LoadStartPage -> loadPage()
            is HomeIntent.LoadNextPage -> loadNextPage()
            is HomeIntent.NewSearchValue -> newSearchValue(intent.value)
            is HomeIntent.ChangeFilter -> setFilter(intent.status, intent.species, intent.type, intent.gender)
        }
    }

    private fun loadPage(page: Int = 1) {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = page == 1,
            )
            val response = searchCharactersUseCase(
                page = page,
                name = _state.value.searchValue,
                status = _state.value.status,
                species = _state.value.species,
                type = _state.value.type,
                gender = _state.value.gender,
            )

            printState(page)

            response.onSuccess { characters ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    error = null,
                    characters = if (page == 1) {
                        characters
                    } else {
                        characters.copy(
                            results = _state.value.characters.results + characters.results
                        )
                    },
                    currentPage = page,
                )
            }
            response.onFailure{ error ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    isLoadingNextPage = false,
                    error = error.message
                )
            }

        }
    }

    private fun loadNextPage() {
        val current = _state.value
        if(!current.isLoadingNextPage) {
            _state.value = current.copy(
                isLoadingNextPage = true
            )
            loadPage(current.currentPage + 1)
        }
    }

    private fun newSearchValue(value: String){
        _state.value = _state.value.copy(
            searchValue = value,
            currentPage = 1
        )
    }

    private fun setFilter(
        status: String?,
        species: String?,
        type: String?,
        gender: String?,
    ){//here
        _state.value = _state.value.copy(
            status = status ?: _state.value.status,
            species = species ?: _state.value.species,
            type = type ?: _state.value.type,
            gender = gender ?: _state.value.gender,
            currentPage = 1,
            characters = CharactersResult(emptyList())
        )
    }

    private fun printState(page: Int){
        val name = _state.value.searchValue
        val status = _state.value.status
        val species = _state.value.species
        val type = _state.value.type
        val gender = _state.value.gender

        Log.d("homeState", "$page, $name, $status, $species, $type, $gender, error = ${_state.value.error}")
    }

}