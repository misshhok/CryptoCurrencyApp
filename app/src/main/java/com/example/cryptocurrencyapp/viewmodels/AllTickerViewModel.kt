package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cryptocurrencyapp.model.CoinLoreClient
import com.example.cryptocurrencyapp.model.response.CurrentCoinDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AllTickerViewModel(private val coinLoreClient: CoinLoreClient) : ViewModel() {

    private var _coinsData = MutableLiveData<List<CurrentCoinDataResponse>>()
    val coinsData: LiveData<List<CurrentCoinDataResponse>> = _coinsData

    init {
        refreshTickers()
    }

    fun refreshTickers() {
        viewModelScope.launch(Dispatchers.IO) {
            _coinsData.postValue(coinLoreClient.getTickers().currentCoinData)
        }
    }

    companion object {

        fun Factory(): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>, extras: CreationExtras
            ): T {
                return AllTickerViewModel(
                    CoinLoreClient.getInstance()
                ) as T
            }
        }
    }
}
