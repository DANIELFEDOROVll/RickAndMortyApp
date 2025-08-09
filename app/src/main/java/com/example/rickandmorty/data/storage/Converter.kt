package com.example.rickandmorty.data.storage

import androidx.room.TypeConverter
import com.example.rickandmorty.data.storage.entity.LocationDbModel
import com.example.rickandmorty.data.storage.entity.OriginDbModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converter {
    val gson = Gson()

    @TypeConverter
    fun fromOriginDbModel(origin: OriginDbModel): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toOriginDbModel(origin: String): OriginDbModel {
        return gson.fromJson(origin, OriginDbModel::class.java)
    }

    @TypeConverter
    fun fromLocationDbModel(location: LocationDbModel): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocationDbModel(location: String): LocationDbModel {
        return gson.fromJson(location, LocationDbModel::class.java)
    }

    @TypeConverter
    fun fromListString(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toListString(json: String): List<String> {
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}