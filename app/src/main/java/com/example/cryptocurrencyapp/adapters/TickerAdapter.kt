package com.example.cryptocurrencyapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.model.favourites.FavouriteCoin
import com.example.cryptocurrencyapp.model.favourites.repository.FavouriteCoinRepository
import com.example.cryptocurrencyapp.model.response.CurrentCoinDataResponse

class TickerAdapter(
    private val navigationToMarketsForCoin: NavigationToMarketsForCoin
) :
    RecyclerView.Adapter<TickerAdapter.CoinDataHolder>() {

    val favouriteRepository = FavouriteCoinRepository.getInstance()

    class CoinDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val coinSymbol: TextView = itemView.findViewById(R.id.coinSymbol)
        val coinName: TextView = itemView.findViewById(R.id.coinName)
        val coinPrice: TextView = itemView.findViewById(R.id.coinPrice)
        val favIcon: ImageView = itemView.findViewById(R.id.favIcon)
    }

    var tickerList: List<CurrentCoinDataResponse> = emptyList()
        @SuppressLint("NotifyDataSetChanged") set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinDataHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.ticker_item, parent, false)
        return CoinDataHolder(itemView)
    }

    override fun getItemCount() = tickerList.size

    override fun onBindViewHolder(holder: CoinDataHolder, position: Int) {
        val coinDataItem = tickerList[position]
        holder.coinSymbol.text = coinDataItem.symbol
        holder.coinName.text = coinDataItem.name
        holder.coinPrice.text =
            "${kotlin.math.round(coinDataItem.priceUsd!!.toDouble() * 1000) / 1000}$"
        holder.itemView.tag = coinDataItem.id
        holder.itemView.setOnClickListener {
            navigationToMarketsForCoin.navigateToMarkets(coinDataItem.id!!)
        }
        holder.favIcon.setOnClickListener {
            if (!favouriteRepository.existsById(coinDataItem.id!!)) {
                favouriteRepository.save(FavouriteCoin(coinDataItem.id))
                Log.i("INFO", "Now in repository ${favouriteRepository.findAll()}")
            } else {
                favouriteRepository.deleteById(coinDataItem.id)
                Log.i("INFO", "Element with id ${coinDataItem.id} removed")
            }
        }
    }
}
