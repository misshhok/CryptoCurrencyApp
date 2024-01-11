package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class TwitterStatsResponse(
    @SerializedName("followers_count") val followersCount: Int?,
    @SerializedName("status_count") val statusCount: Int?
) {

    override fun toString(): String = """
        Followers on Twitter: $followersCount
        """.trimIndent()
}
