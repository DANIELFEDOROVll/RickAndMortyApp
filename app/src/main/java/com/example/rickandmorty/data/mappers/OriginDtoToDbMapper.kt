package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.OriginDto
import com.example.rickandmorty.data.storage.entity.OriginDbModel

fun OriginDto.toDbModel() = OriginDbModel(
    name = name,
    url = url,
)