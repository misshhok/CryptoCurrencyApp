package com.example.cryptocurrencyapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.adapters.FavouriteTickersAdapter
import com.example.cryptocurrencyapp.adapters.NavigationToMarketsForCoin
import com.example.cryptocurrencyapp.databinding.FragmentFavouritesBinding
import com.example.cryptocurrencyapp.viewmodels.FavouriteTickersViewModel


class FavouriteTickersFragment : Fragment() {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favTickerAdapter: FavouriteTickersAdapter
    private val viewModel: FavouriteTickersViewModel by viewModels { FavouriteTickersViewModel.Factory() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)
        binding.tickersRecycler.layoutManager = LinearLayoutManager(context)
        favTickerAdapter = FavouriteTickersAdapter(actionToMarkets).apply {
            viewModel.coinsData.observe(viewLifecycleOwner) {
                tickerList = it
            }
        }
        binding.tickersRecycler.adapter = favTickerAdapter
        binding.refreshButton.setOnClickListener {
            viewModel.refreshFavTickers()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private val actionToMarkets = object : NavigationToMarketsForCoin {
        override fun navigateToMarkets(coinId: String) {
            val action =
                AllTickersFragmentDirections.actionAllTickersFragmentToMarketsForCoinFragment(coinId)
            findNavController().navigate(action)
        }
    }
}
