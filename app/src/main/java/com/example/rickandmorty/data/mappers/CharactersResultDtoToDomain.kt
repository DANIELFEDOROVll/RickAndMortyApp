package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.CharactersResultDto
import com.example.rickandmorty.domain.CharactersResult

fun CharactersResultDto.toDomain() = CharactersResult(
    results = results.map { it.toDomain() }
)