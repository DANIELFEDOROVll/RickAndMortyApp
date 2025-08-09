package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.storage.entity.CharacterDbModel
import com.example.rickandmorty.domain.Character


fun CharacterDbModel.toDomain() = Character(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    type = type,
    image = image,
    url = url,
    origin = origin.toDomain(),
    location = location.toDomain(),
    episode = episode,
    created = created
)
