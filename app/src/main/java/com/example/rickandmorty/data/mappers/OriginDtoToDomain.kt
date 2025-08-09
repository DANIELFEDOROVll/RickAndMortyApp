package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.OriginDto
import com.example.rickandmorty.domain.Origin

fun OriginDto.toDomain() = Origin(
    name = name,
    url = url,
)