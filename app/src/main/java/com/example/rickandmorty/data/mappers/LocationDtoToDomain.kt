package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.LocationDto
import com.example.rickandmorty.domain.Location

fun LocationDto.toDomain() = Location(
    name = name,
    url = url,
)