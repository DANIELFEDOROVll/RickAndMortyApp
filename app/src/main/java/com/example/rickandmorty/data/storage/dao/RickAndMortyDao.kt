package com.example.rickandmorty.data.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.data.storage.entity.CharacterDbModel


@Dao
interface RickAndMortyDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun cacheCharactersPage(page: List<CharacterDbModel>)

    @Query("""
        SELECT * FROM character
        WHERE 
            page = :page
            AND (:name = "" OR LOWER(name) LIKE '%' || LOWER(:name) || '%')
            AND (:status = "" OR status = :status)
            AND (:species = "" OR species = :species)
            AND (:type = "" OR type = :type)
            AND (:gender = "" OR gender = :gender)
    """)
    suspend fun searchCharacters(
        page: Int = 1,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String,
    ): List<CharacterDbModel>?

    @Query("SELECT * FROM character WHERE id = :characterId")
    suspend fun getCharacterById(characterId: Int): CharacterDbModel?
}