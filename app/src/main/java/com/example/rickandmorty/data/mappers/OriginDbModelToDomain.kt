package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.storage.entity.OriginDbModel
import com.example.rickandmorty.domain.Origin

fun OriginDbModel.toDomain() = Origin(
    name = name,
    url = url,
)