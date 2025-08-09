package com.example.rickandmorty.di

import androidx.room.Room
import com.example.rickandmorty.data.storage.dao.RickAndMortyDao
import com.example.rickandmorty.data.storage.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "database-name"
        ).build()
    }

    single<RickAndMortyDao> {
        get<AppDatabase>().rickAndMortyDao()
    }
}