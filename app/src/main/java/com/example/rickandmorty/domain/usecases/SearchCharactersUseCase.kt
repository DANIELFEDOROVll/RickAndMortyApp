package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.CharactersResult
import com.example.rickandmorty.domain.RickAndMortyRepository

class SearchCharactersUseCase(
    private val repo: RickAndMortyRepository
) {
    suspend operator fun invoke(
        page: Int = 0,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
    ): Result<CharactersResult> {
        return repo.searchCharacters(
            page = page,
            name = name,
            status = status,
            species = species,
            type = type,
            gender = gender,
        )
    }
}