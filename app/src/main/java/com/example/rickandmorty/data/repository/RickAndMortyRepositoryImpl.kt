package com.example.rickandmorty.data.repository


import android.util.Log
import com.example.rickandmorty.data.mappers.toDbModel
import com.example.rickandmorty.data.mappers.toDomain
import com.example.rickandmorty.data.network.services.RickAndMortyService
import com.example.rickandmorty.data.storage.dao.RickAndMortyDao
import com.example.rickandmorty.domain.Character
import com.example.rickandmorty.domain.CharactersResult
import com.example.rickandmorty.domain.RickAndMortyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException


class RickAndMortyRepositoryImpl(
    private val api: RickAndMortyService,
    private val dao: RickAndMortyDao
): RickAndMortyRepository{
    override suspend fun searchCharacters(
        page: Int,
        name: String,
        status: String,
        species: String,
        type: String,
        gender: String
    ): Result<CharactersResult> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCharacters(
                    page = page,
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender,
                )

                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        dao.cacheCharactersPage(body.results.map { it.toDbModel(page) })
                        Result.success(body.toDomain())
                    }
                    else{
                        Log.d("searchCharacters", "bodyIsNull")
                        Result.failure(Exception("body is null"))
                    }
                }
                else{
                    val charactersFromCache = dao.searchCharacters(
                        page = page,
                        name = name,
                        status = status,
                        species = species,
                        type = type,
                        gender = gender,
                    )
                    Log.d("charactersFromCache", charactersFromCache.toString())
                    if(charactersFromCache != null){
                        Result.success(CharactersResult(
                            charactersFromCache.map { it.toDomain() }
                        ))
                    }
                    else{
                        val errorMessage = response.errorBody().toString()
                        Result.failure(IOException("Ошибка сервера: $errorMessage"))
                    }
                }
            } catch (e: IOException){
                val charactersFromCache = dao.searchCharacters(
                    page = page,
                    name = name,
                    status = status,
                    species = species,
                    type = type,
                    gender = gender,
                )
                Log.d("charactersFromCache", charactersFromCache.toString())
                if(!charactersFromCache.isNullOrEmpty()){
                    Result.success(CharactersResult(
                        charactersFromCache.map { it.toDomain() }
                    ))
                }
                else{
                    Result.failure(IOException("Ошибка сервера, кэш пустой: ${e.message}"))
                }
            } catch (e: Exception){
                Result.failure(Exception("Неизвестная ошибка: ${e.message}"))
            }
        }
    }

    override suspend fun getCharacterById(characterId: Int): Result<Character> {
        return withContext(Dispatchers.IO){
            try {
                val response = api.getCharacterById(characterId)
                if(response.isSuccessful){
                    val body = response.body()
                    if(body != null){
                        Result.success(body.toDomain())
                    }
                    else{
                        Result.failure(Exception("body is null"))
                    }
                }
                else{
                    Log.d("searchCharacters", "Кэш")
                    val characterFromCache = dao.getCharacterById(characterId)
                    if(characterFromCache != null){
                        Result.success(characterFromCache.toDomain())
                    }
                    else{
                        val errorMessage = response.errorBody().toString()
                        Result.failure(IOException("Ошибка сервера: $errorMessage"))
                    }
                }
            } catch (e: IOException){
                val characterFromCache = dao.getCharacterById(characterId)
                if(characterFromCache != null){
                    Result.success(characterFromCache.toDomain())
                }
                else{
                    Result.failure(IOException("Ошибка сервера, кэш пустой: ${e.message}"))
                }
            } catch (e: Exception){
                Result.failure(Exception("Неизвестная ошибка: ${e.message}"))
            }
        }
    }

}