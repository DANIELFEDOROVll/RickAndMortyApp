package com.example.rickandmorty.domain

interface RickAndMortyRepository {
    suspend fun searchCharacters(
        page: Int = 1,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
    ): Result<CharactersResult>

    suspend fun getCharacterById(characterId: Int): Result<Character>
}