package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.CharacterDto
import com.example.rickandmorty.domain.Character

fun CharacterDto.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    type = type,
    origin = origin.toDomain(),
    location = location.toDomain(),
    image = image,
    url = url,
    episode = episode,
    created = created
)