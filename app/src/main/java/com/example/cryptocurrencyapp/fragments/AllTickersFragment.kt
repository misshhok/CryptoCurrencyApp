package com.example.cryptocurrencyapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.adapters.TickerAdapter
import com.example.cryptocurrencyapp.databinding.FragmentAllTickersBinding
import com.example.cryptocurrencyapp.viewmodels.AllTickerViewModel


class AllTickersFragment : Fragment() {
    private var _binding: FragmentAllTickersBinding? = null
    private val binding get() = _binding!!

    private lateinit var tickerAdapter: TickerAdapter
    private val viewModel: AllTickerViewModel by viewModels { AllTickerViewModel.Factory() }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTickersBinding.inflate(layoutInflater, container, false)
        binding.coinsDataRecycler.layoutManager = LinearLayoutManager(context)
        tickerAdapter = TickerAdapter().apply {
            viewModel.coinsDataList.observe(viewLifecycleOwner) {
                tickerList = it
            }
        }
        binding.coinsDataRecycler.adapter = tickerAdapter
        binding.refreshButton.setOnClickListener {
            viewModel.refreshTickers()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
