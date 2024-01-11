package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class ExchangeDataResponse(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("name_id") val nameId: String,
    @SerializedName("volume_usd") val volumeUsd: Double,
    @SerializedName("active_pairs") val activePairs: Int,
    @SerializedName("url") val url: String,
    @SerializedName("country") val country: String
) {

    override fun toString(): String = """
            ID: $id
            Name: $name
            Name ID: $nameId
            Volume, $: $volumeUsd
            Active pairs: $activePairs
            Site URL: $url
            Country: $country
        """.trimIndent()
}
