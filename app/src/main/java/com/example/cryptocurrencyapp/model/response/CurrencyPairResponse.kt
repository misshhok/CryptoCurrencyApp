package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class CurrencyPairResponse(
    @SerializedName("base") val base: String?,
    @SerializedName("quote") val quote: String?,
    @SerializedName("volume") val volume: Double,
    @SerializedName("price") val price: Double,
    @SerializedName("price_usd") val priceUsd: Double,
    @SerializedName("time") val time: Long
)
