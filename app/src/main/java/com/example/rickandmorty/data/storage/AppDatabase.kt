package com.example.rickandmorty.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rickandmorty.data.storage.dao.RickAndMortyDao
import com.example.rickandmorty.data.storage.entity.CharacterDbModel

@Database(entities = [CharacterDbModel::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rickAndMortyDao(): RickAndMortyDao
}