package com.example.cryptocurrencyapp.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptocurrencyapp.adapters.PairAdapter
import com.example.cryptocurrencyapp.databinding.FragmentExchangeBinding
import com.example.cryptocurrencyapp.viewmodels.ExchangeViewModel

class ExchangeFragment : Fragment() {

    private var _binding: FragmentExchangeBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ExchangeFragmentArgs>()
    private val exchangeName by lazy { args.exchangeName }
    private lateinit var pairAdapter: PairAdapter
    private val viewModel: ExchangeViewModel by viewModels { ExchangeViewModel.Factory(exchangeName) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExchangeBinding.inflate(layoutInflater, container, false)

        viewModel.exchange.observe(viewLifecycleOwner) {
            binding.header.text = it.name
        }

        binding.currencyPairsRecycler.layoutManager = LinearLayoutManager(context)
        pairAdapter = PairAdapter().apply {
            viewModel.pairs.observe(viewLifecycleOwner) {
                pairs = it
            }
        }
        binding.currencyPairsRecycler.adapter = pairAdapter

        binding.refreshButton.setOnClickListener {
            viewModel.refreshExchange()
        }

        binding.header.setOnClickListener {
            AlertDialog.Builder(binding.header.context)
                .setTitle("Exchange data:")
                .setMessage(
                    viewModel.exchange.value.toString()
                )
                .setPositiveButton("Close") { _, _ -> }.show()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
