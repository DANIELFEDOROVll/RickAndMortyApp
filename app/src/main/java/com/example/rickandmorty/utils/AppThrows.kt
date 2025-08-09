package com.example.rickandmorty.utils

sealed class AppThrows: Throwable(){
    data class BodyIsNullException(override val message: String) : AppThrows()
    data class BeyondLastPage(override val message: String): AppThrows()

}