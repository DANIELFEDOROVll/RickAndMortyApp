//package com.example.rickandmorty.data.mappers
//
//import com.example.rickandmorty.data.storage.entity.PageWithCharacters
//import com.example.rickandmorty.domain.CharactersResult
//import com.example.rickandmorty.domain.Info
//
//fun PageWithCharacters.toDomain() = CharactersResult(
//    info = Info(0,0, "", ""),
//    results = characters.map { it.toDomain() }
//)