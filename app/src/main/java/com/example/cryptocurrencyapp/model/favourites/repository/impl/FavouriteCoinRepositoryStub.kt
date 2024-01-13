package com.example.cryptocurrencyapp.model.favourites.repository.impl

import androidx.annotation.RequiresApi
import com.example.cryptocurrencyapp.model.favourites.FavouriteCoin
import com.example.cryptocurrencyapp.model.favourites.repository.FavouriteCoinRepository
import java.util.stream.Collectors

class FavouriteCoinRepositoryStub : FavouriteCoinRepository {

    private var favouriteCoins = mutableListOf<FavouriteCoin>()

    override fun save(favouriteCoin: FavouriteCoin) {
        favouriteCoins.add(favouriteCoin)
    }

    override fun findAll() : List<FavouriteCoin> = favouriteCoins
    override fun existsById(coinId: String) : Boolean {
        for (i in favouriteCoins) {
            if (i.coinId == coinId) return true
        }
        return false
    }

    @RequiresApi(34)
    override fun deleteById(coinId: String) {
        favouriteCoins = favouriteCoins.stream()
            .filter { it.coinId != coinId }
            .collect(Collectors.toList())
    }
}

