package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class CoinsAmountByTimeResponse(
    @SerializedName("coins_num") val coinsNum: Int,
    @SerializedName("time") val time: Long
)
