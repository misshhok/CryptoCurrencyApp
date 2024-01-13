package com.example.cryptocurrencyapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.cryptocurrencyapp.model.CoinLoreClient
import com.example.cryptocurrencyapp.model.response.CurrencyPairResponse
import com.example.cryptocurrencyapp.model.response.ExchangeDataResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ExchangeViewModel(
    private val exchangeName: String, private val coinLoreClient: CoinLoreClient
) : ViewModel() {

    private var _exchange = MutableLiveData<ExchangeDataResponse>()
    val exchange: LiveData<ExchangeDataResponse> = _exchange
    private var _pairs = MutableLiveData<List<CurrencyPairResponse>>()
    val pairs: LiveData<List<CurrencyPairResponse>> = _pairs

    init {
        refreshExchange()
    }

    fun refreshExchange() {
        viewModelScope.launch(Dispatchers.IO) {
            val exchange = findExchangeByName(exchangeName)
            _exchange.postValue(exchange)
            val currencyPairs = getCurrencyPairsByExchangeId(exchange.id)
            _pairs.postValue(currencyPairs)
        }
    }

    private suspend fun findExchangeByName(exchangeName: String): ExchangeDataResponse =
        withContext(Dispatchers.IO) {
            coinLoreClient.getExchanges().values.find { it.name == exchangeName }!!
        }

    private suspend fun getCurrencyPairsByExchangeId(exchangeId: String): List<CurrencyPairResponse> =
        withContext(Dispatchers.IO) {
            coinLoreClient.getExchangeDataById(exchangeId).currencyPairs
        }

    companion object {

        fun Factory(exchangeName: String): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    modelClass: Class<T>, extras: CreationExtras
                ): T {
                    return ExchangeViewModel(
                        exchangeName, CoinLoreClient.getInstance()
                    ) as T
                }
            }
    }
}
