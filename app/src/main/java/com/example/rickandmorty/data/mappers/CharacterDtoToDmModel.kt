package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.CharacterDto
import com.example.rickandmorty.data.storage.entity.CharacterDbModel

fun CharacterDto.toDbModel(page: Int): CharacterDbModel{
    return CharacterDbModel(
        id = id,
        page = page,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin.toDbModel(),
        location = location.toDbModel(),
        image = image,
        episode = episode,
        url = url,
        created = created,
    )
}