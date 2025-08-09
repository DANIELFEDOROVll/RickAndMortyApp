package com.example.rickandmorty.di

import com.example.rickandmorty.presentation.screens.characterDetail.CharacterDetailViewModel
import com.example.rickandmorty.presentation.screens.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        HomeViewModel(get())
    }
    viewModel { (id: Int) ->
        CharacterDetailViewModel(
            id,
            get()
        )
    }
}