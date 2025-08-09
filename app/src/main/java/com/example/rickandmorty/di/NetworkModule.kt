package com.example.rickandmorty.di

import com.example.rickandmorty.data.network.services.RickAndMortyService
import com.example.rickandmorty.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        OkHttpClient.Builder()
            .readTimeout(6, TimeUnit.SECONDS)
            .connectTimeout(6, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single{
        get<Retrofit>().create(RickAndMortyService::class.java)
    }
}