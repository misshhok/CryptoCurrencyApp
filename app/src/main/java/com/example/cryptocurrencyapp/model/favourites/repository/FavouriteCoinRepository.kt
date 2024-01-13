package com.example.cryptocurrencyapp.model.favourites.repository

import com.example.cryptocurrencyapp.model.favourites.FavouriteCoin
import com.example.cryptocurrencyapp.model.favourites.repository.impl.FavouriteCoinRepositoryStub

interface FavouriteCoinRepository {
    fun save(favouriteCoin: FavouriteCoin)
    fun findAll() : List<FavouriteCoin>
    fun existsById(coinId: String): Boolean
    fun deleteById(coinId: String)

    companion object {

        @Volatile
        private var instance: FavouriteCoinRepositoryStub? = null

        fun getInstance(): FavouriteCoinRepository = instance ?: synchronized(this) {
            instance ?: FavouriteCoinRepositoryStub().also {
                instance = it
            }
        }
    }
}
