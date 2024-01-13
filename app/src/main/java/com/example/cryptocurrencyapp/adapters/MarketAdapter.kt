package com.example.cryptocurrencyapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.model.response.MarketForCoinResponse

class MarketAdapter(private val navigationToExchange: NavigationToExchange) :
    RecyclerView.Adapter<MarketAdapter.MarketHolder>() {

    class MarketHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val marketName: TextView = itemView.findViewById(R.id.marketName)
        val marketCoinPrice: TextView = itemView.findViewById(R.id.marketCoinPrice)
        val marketCoinPriceQuote: TextView = itemView.findViewById(R.id.marketCoinPriceQuote)
    }

    var marketsForCoinList: List<MarketForCoinResponse> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarketHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.market_item, parent, false)
        return MarketHolder(itemView)
    }

    override fun getItemCount() = marketsForCoinList.size

    override fun onBindViewHolder(holder: MarketHolder, position: Int) {
        val marketItem = marketsForCoinList[position]
        holder.itemView.tag = marketItem.name
        holder.marketName.text = marketItem.name
        holder.marketCoinPrice.text =
            "${marketItem.base} ${kotlin.math.round(marketItem.priceUsd * 1000) / 1000}$"
        holder.marketCoinPriceQuote.text =
            "${marketItem.base} ${kotlin.math.round(marketItem.price * 1000) / 1000} ${marketItem.quote}"
        holder.itemView.setOnClickListener {
            navigationToExchange.navigateToExchange(marketItem.name)
        }
    }
}
