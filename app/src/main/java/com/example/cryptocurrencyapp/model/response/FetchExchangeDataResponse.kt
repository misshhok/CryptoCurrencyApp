package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class FetchExchangeDataResponse(
    @SerializedName("0") val marketInfo: MarketInfoResponse?,
    @SerializedName("pairs") val currencyPairs: List<CurrencyPairResponse>
)
