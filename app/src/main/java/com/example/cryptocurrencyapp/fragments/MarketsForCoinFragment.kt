package com.example.cryptocurrencyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.adapters.MarketAdapter
import com.example.cryptocurrencyapp.adapters.NavigationToExchange
import com.example.cryptocurrencyapp.databinding.FragmentMarketsForCoinBinding
import com.example.cryptocurrencyapp.viewmodels.MarketsForCoinViewModel


class MarketsForCoinFragment : Fragment() {

    private var _binding: FragmentMarketsForCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var marketAdapter: MarketAdapter
    private val args by navArgs<MarketsForCoinFragmentArgs>()
    private val coinId by lazy { args.coinId }
    private val viewModel: MarketsForCoinViewModel by viewModels {
        MarketsForCoinViewModel.Factory(coinId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketsForCoinBinding.inflate(layoutInflater, container, false)
        binding.marketsRecycler.layoutManager = LinearLayoutManager(context)
        marketAdapter = MarketAdapter(actionToExchange).apply {
            viewModel.marketsForCoin.observe(viewLifecycleOwner) {
                marketsForCoinList = it
            }
        }
        binding.marketsRecycler.adapter = marketAdapter
        binding.refreshButton.setOnClickListener {
            viewModel.refreshMarkets()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val actionToExchange = object : NavigationToExchange {
        override fun navigateToExchange(exchangeName: String) {
            val action =
                MarketsForCoinFragmentDirections.actionMarketsForCoinFragmentToExchangeFragment(
                    exchangeName
                )
            findNavController().navigate(action)
        }
    }
}
