package com.example.cryptocurrencyapp.model

import com.example.cryptocurrencyapp.model.response.AllCurrentTickersResponse
import com.example.cryptocurrencyapp.model.response.CurrentCoinDataResponse
import com.example.cryptocurrencyapp.model.response.ExchangeDataResponse
import com.example.cryptocurrencyapp.model.response.FetchExchangeDataResponse
import com.example.cryptocurrencyapp.model.response.MarketForCoinResponse
import com.example.cryptocurrencyapp.model.response.SocialStatsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinLoreClient {
    @GET("coin/social_stats/")
    suspend fun getSocialStats(@Query("id") coinId: String): SocialStatsResponse
    @GET("tickers/")
    suspend fun getTickers(): AllCurrentTickersResponse
    @GET("exchange/")
    suspend fun getExchangeDataById(@Query("id") exchangeId: String): FetchExchangeDataResponse
    @GET("exchanges/")
    suspend fun getExchanges(): Map<String, ExchangeDataResponse>
    @GET("ticker/")
    suspend fun getCurrentCoinDataById(@Query("id") coinId: String): List<CurrentCoinDataResponse>
    @GET("coin/markets/")
    suspend fun getMarketsForCoinById(@Query("id") coinId: String): List<MarketForCoinResponse>

    companion object {
        fun getInstance(): CoinLoreClient {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.coinlore.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CoinLoreClient::class.java)
        }
    }
}
