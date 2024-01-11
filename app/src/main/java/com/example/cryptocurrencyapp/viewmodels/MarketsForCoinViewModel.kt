package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cryptocurrencyapp.model.CoinLoreClient
import com.example.cryptocurrencyapp.model.response.MarketForCoinResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MarketsForCoinViewModel (
    private val coinId: String, private val coinLoreClient: CoinLoreClient
) : ViewModel() {

    private var _marketsForCoinList = MutableLiveData<List<MarketForCoinResponse>>()
    val marketsForCoinList: LiveData<List<MarketForCoinResponse>> = _marketsForCoinList

    init {
        refreshMarkets()
    }

    fun refreshMarkets() {
        viewModelScope.launch(Dispatchers.IO) {
            val marketsForCoin = coinLoreClient.getMarketsForCoinById(coinId)
            _marketsForCoinList.postValue(marketsForCoin)
        }
    }

    companion object {

        fun Factory(coinId: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    return MarketsForCoinViewModel(
                        coinId,
                        CoinLoreClient.getInstance()
                    ) as T
                }
            }
    }
}
