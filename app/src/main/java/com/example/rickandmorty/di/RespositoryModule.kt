package com.example.rickandmorty.di

import com.example.rickandmorty.data.repository.RickAndMortyRepositoryImpl
import com.example.rickandmorty.domain.RickAndMortyRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<RickAndMortyRepository> {
        RickAndMortyRepositoryImpl(
            get(),
            get(),
        )
    }
}