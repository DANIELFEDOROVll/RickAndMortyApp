//package com.example.rickandmorty.data.mappers
//
//import com.example.rickandmorty.data.network.entities.CharactersResultDto
//import com.example.rickandmorty.data.storage.entity.CharactersResultDbModel
//
//fun CharactersResultDto.toDbModel(page: Int): CharactersResultDbModel{
//    return CharactersResultDbModel(
//        page = page,
//        nextPage = page + 1
//    )
//}