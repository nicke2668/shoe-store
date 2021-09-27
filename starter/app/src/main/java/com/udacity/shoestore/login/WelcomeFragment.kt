package com.example.shoestore.ui.login

import androidx.fragment.app.Fragment
import android.R

import android.os.Bundle

import android.view.ViewGroup

import android.view.LayoutInflater

import androidx.annotation.NonNull




class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val binding: DashboardFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.dashboard_fragment, container, false
        )
        val forecastAdapter = ForecastAdapter(getUnitUserPreference(requireContext()))
        binding.forecastRecyclerView.setAdapter(forecastAdapter)
        binding.forecastRecyclerView.setHasFixedSize(true)
        setupViewModel()
        viewModel.getForecast().observe(this, forecastAdapter::setList)
        viewModel.getTodayForecast().observe(this) { forecast ->
            if (forecast == null) return@observe
            binding.setModel(forecast)
            binding.setConverter(DisplayValueConverter(getUnitUserPreference(requireContext())))
        }
        setHasOptionsMenu(true)
        initializeAds(binding)
        return binding.getRoot()
    }


}