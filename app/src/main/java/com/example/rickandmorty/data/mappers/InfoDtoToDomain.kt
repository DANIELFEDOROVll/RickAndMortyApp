package com.example.rickandmorty.data.mappers

import com.example.rickandmorty.data.network.entities.InfoDto
import com.example.rickandmorty.domain.Info

fun InfoDto.toDomain() = Info(
    count = count,
    pages = pages,
    next = next,
    prev = prev,
)