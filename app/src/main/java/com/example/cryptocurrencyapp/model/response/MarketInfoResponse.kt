package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class MarketInfoResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("date_live") val dateLive: String?,
    @SerializedName("url") val url: String?
)
