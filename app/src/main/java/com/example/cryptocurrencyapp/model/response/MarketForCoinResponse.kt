package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class MarketForCoinResponse(
    @SerializedName("name") val name: String,
    @SerializedName("base") val base: String,
    @SerializedName("quote") val quote: String,
    @SerializedName("price") val price: Double,
    @SerializedName("price_usd") val priceUsd: Double,
    @SerializedName("volume") val volume: Double,
    @SerializedName("volume_usd") val volumeUsd: Double,
    @SerializedName("time") val time: Long
) {

    override fun toString(): String = """
            Name: $name
            Base: $base
            Quote: $quote
            Price: $price
            Price, $: $priceUsd
            Volume: $volume
            Volume, $: $volumeUsd
            Time: $time
        """.trimIndent()
}
