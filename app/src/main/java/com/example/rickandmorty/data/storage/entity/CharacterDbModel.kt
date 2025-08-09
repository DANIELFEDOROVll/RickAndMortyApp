package com.example.rickandmorty.data.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.rickandmorty.data.storage.Converter

@Entity(tableName = "character")
data class CharacterDbModel(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    @TypeConverters(Converter::class)
    val origin: OriginDbModel,
    @TypeConverters(Converter::class)
    val location: LocationDbModel,
    val image: String,
    @TypeConverters(Converter::class)
    val episode: List<String>,
    val url: String,
    val created: String,
)
