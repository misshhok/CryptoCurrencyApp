package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class SocialStatsResponse(
    @SerializedName("reddit") val redditStats: RedditStatsResponse?,
    @SerializedName("twitter") val twitterStats: TwitterStatsResponse?
) {

    override fun toString() =
        redditStats.toString() + '\n' + twitterStats.toString()
}
