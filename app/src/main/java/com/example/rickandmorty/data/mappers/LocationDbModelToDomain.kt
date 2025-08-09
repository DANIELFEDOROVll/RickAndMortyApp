package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.storage.entity.LocationDbModel
import com.example.rickandmorty.domain.Location

fun LocationDbModel.toDomain() = Location(
    name = name,
    url = url,
)