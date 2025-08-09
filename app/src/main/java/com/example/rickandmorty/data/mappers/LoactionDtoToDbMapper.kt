package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.LocationDto
import com.example.rickandmorty.data.storage.entity.LocationDbModel

fun LocationDto.toDbModel() = LocationDbModel(
    name = name,
    url = url,
)