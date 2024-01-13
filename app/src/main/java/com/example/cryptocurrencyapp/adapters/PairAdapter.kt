package com.example.cryptocurrencyapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencyapp.R
import com.example.cryptocurrencyapp.model.response.CurrencyPairResponse

class PairAdapter : RecyclerView.Adapter<PairAdapter.PairListItem>() {

    class PairListItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cpBase: TextView = itemView.findViewById(R.id.cpBase)
        val cpQuote: TextView = itemView.findViewById(R.id.cpQuote)
        val cpVolume: TextView = itemView.findViewById(R.id.cpVolume)
        val cpPrice: TextView = itemView.findViewById(R.id.cpPrice)
        val cpPriceUsd: TextView = itemView.findViewById(R.id.cpPriceUsd)
        val cpTime: TextView = itemView.findViewById(R.id.cpTime)
    }

    var pairs: List<CurrencyPairResponse> = emptyList()
    @SuppressLint("NotifyDataSetChanged") set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PairListItem {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.pair_item, parent, false)
        return PairListItem(itemView)
    }

    override fun getItemCount() = pairs.size

    override fun onBindViewHolder(holder: PairListItem, position: Int) {
        val currencyPairItem = pairs[position]
        holder.cpBase.text = "Base: ${currencyPairItem.base}"
        holder.cpQuote.text = "Quote: ${currencyPairItem.quote}"
        holder.cpVolume.text = "Volume: ${currencyPairItem.volume}"
        holder.cpPrice.text = "Price: ${kotlin.math.round(currencyPairItem.price!!.toDouble() * 1000) / 1000}\$"
        holder.cpPriceUsd.text = "Price, $: ${kotlin.math.round(currencyPairItem.priceUsd!!.toDouble() * 1000) / 1000}\$"
        holder.cpTime.text = "Time: ${currencyPairItem.time}"
    }
}
