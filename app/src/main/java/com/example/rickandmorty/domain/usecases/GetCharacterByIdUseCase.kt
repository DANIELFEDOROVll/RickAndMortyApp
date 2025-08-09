package com.example.rickandmorty.domain.usecases

import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.RickAndMortyRepository

class GetCharacterByIdUseCase(
    private val repo: RickAndMortyRepository
) {
    suspend operator fun invoke(characterId: Int): Result<Character>{
        return repo.getCharacterById(characterId)
    }
}