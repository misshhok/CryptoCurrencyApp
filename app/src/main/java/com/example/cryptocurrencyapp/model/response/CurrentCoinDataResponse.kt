package com.example.cryptocurrencyapp.model.response

import com.google.gson.annotations.SerializedName

data class CurrentCoinDataResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("symbol") val symbol: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("nameid") val nameId: String?,
    @SerializedName("rank") val rank: Int,
    @SerializedName("price_usd") val priceUsd: String?,
    @SerializedName("percent_change_24h") val percentChange24h: String?,
    @SerializedName("percent_change_1h") val percentChange1h: String?,
    @SerializedName("percent_change_7d") val percentChange7d: String?,
    @SerializedName("price_btc") val priceBtc: String?,
    @SerializedName("market_cap_usd") val marketCapUsd: String?,
    @SerializedName("volume24") val volume24: Double,
    @SerializedName("volume24a") val volume24a: Double,
    @SerializedName("csupply") val csupply: String?,
    @SerializedName("tsupply") val tsupply: String?,
    @SerializedName("msupply") val msupply: String?
) {

    override fun toString(): String = """
            ID: $id
            Symbol: $symbol
            Name: $name
            Name ID: $nameId
            Rank: $rank
            Price: $priceUsd $ / $priceBtc BTC
            Market capital.: $marketCapUsd USD
            1-hour change: $percentChange1h %
            24-hours change: $percentChange24h %
            7-days change: $percentChange7d %
            Bargaining volume 24h.: $volume24
            Bargaining volume 24h., alt.: $volume24a
            Supplied: $csupply
            Supplying: $tsupply
            Will be supplied: $msupply
        """.trimIndent()

    fun getShortData(): String = """
            Symbol: $symbol
            Name: $name
            Rank: $rank
            Price: $priceUsd $ / $priceBtc BTC
            Market capital.: $marketCapUsd USD
            1-hour change: $percentChange1h %
            24-hours change: $percentChange24h %
            7-days change: $percentChange7d %
            Bargaining volume 24h.: $volume24
            Bargaining volume 24h., alt.: $volume24a
        """.trimIndent()
}
