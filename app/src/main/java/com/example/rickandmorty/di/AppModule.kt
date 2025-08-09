package com.example.rickandmorty.di

import com.example.rickandmorty.domain.usecases.GetCharacterByIdUseCase
import com.example.rickandmorty.domain.usecases.SearchCharactersUseCase
import org.koin.dsl.module

val appModule = module {
    single {
        SearchCharactersUseCase(get())
    }
    single {
        GetCharacterByIdUseCase(get())
    }
}