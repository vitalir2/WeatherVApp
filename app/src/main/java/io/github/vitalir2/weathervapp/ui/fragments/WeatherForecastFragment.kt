package io.github.vitalir2.weathervapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.github.vitalir2.weathervapp.adapters.WeatherForecastAdapter
import io.github.vitalir2.weathervapp.databinding.FragmentWeatherForecastBinding
import io.github.vitalir2.weathervapp.viewmodels.WeatherForecastViewModel

@AndroidEntryPoint
class WeatherForecastFragment : Fragment() {

    private val TAG = "WeatherForecastFragment"

    private var _binding: FragmentWeatherForecastBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherForecastViewModel by viewModels()
    private lateinit var adapter: WeatherForecastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        // Temporary coordinates of Moscow
        val moscowLatitude = 55.751244
        val moscowLongitude = 37.618423
        if (viewModel.forecasts.value == null) {
            viewModel.getWeatherForecast(moscowLatitude, moscowLongitude)
        }
        observeForecasts()
    }

    private fun setRecyclerView() {
        adapter = WeatherForecastAdapter()
        val layoutManager = LinearLayoutManager(context)
        binding.forecastRecycler.layoutManager = layoutManager
        binding.forecastRecycler.adapter = adapter
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.forecastRecycler.addItemDecoration(itemDecoration)
    }

    private fun observeForecasts() {
        viewModel.forecasts.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}