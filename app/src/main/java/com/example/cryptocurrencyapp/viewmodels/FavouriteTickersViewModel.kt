package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cryptocurrencyapp.model.CoinLoreClient
import com.example.cryptocurrencyapp.model.favourites.repository.FavouriteCoinRepository
import com.example.cryptocurrencyapp.model.response.CurrentCoinDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors

class FavouriteTickersViewModel(private val coinLoreClient: CoinLoreClient) : ViewModel() {

    private var _coinsData = MutableLiveData<List<CurrentCoinDataResponse>>()
    val coinsData: LiveData<List<CurrentCoinDataResponse>> = _coinsData
    val favouriteCoinRepository = FavouriteCoinRepository.getInstance()

    init {
        refreshFavTickers()
    }

    fun refreshFavTickers() {
        viewModelScope.launch(Dispatchers.IO) {
            val coinsFromApi = coinLoreClient.getTickers().currentCoinData
                .stream()
                .filter {
                    favouriteCoinRepository.existsById(it.id!!)
                }
                .collect(Collectors.toList())
            _coinsData.postValue(coinsFromApi)
        }
    }

    companion object {

        fun Factory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                return FavouriteTickersViewModel(
                    CoinLoreClient.getInstance()
                ) as T
            }
        }
    }
}
