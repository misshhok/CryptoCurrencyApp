package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class AllCurrentTickersResponse(
    @SerializedName("data") val currentCoinData: List<CurrentCoinDataResponse>,
    @SerializedName("info") val coinsAmountByTime: CoinsAmountByTimeResponse
)
