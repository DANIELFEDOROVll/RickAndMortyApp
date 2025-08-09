package com.example.rickandmorty.data.network.services

import com.example.rickandmorty.data.network.entities.CharacterDto
import com.example.rickandmorty.data.network.entities.CharactersResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("species") species: String? = null,
        @Query("type") type: String? = null,
        @Query("gender") gender: String? = null
    ): Response<CharactersResultDto>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<CharacterDto>
}