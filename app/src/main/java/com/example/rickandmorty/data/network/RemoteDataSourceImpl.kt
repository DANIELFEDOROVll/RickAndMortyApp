//package com.example.rickandmorty.data.network
//
//import com.example.rickandmorty.data.network.entities.CharacterDto
//import com.example.rickandmorty.data.network.entities.CharactersResultDto
//import com.example.rickandmorty.data.network.services.RickAndMortyService
//
//class RemoteDataSourceImpl(
//    private val api: RickAndMortyService
//): RemoteDataSource {
//
//    override suspend fun getPageCharacters(page: Int): CharactersResultDto? {
//        val response = api.getPageCharacters(page)
//        if(response.isSuccessful){
//            return response.body()
//        }
//        return null
//    }
//
//    override suspend fun getCharacterById(characterId: Int): CharacterDto {
//        TODO("Not yet implemented")
//    }
//}