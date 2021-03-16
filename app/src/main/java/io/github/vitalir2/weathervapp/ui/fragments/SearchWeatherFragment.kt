package io.github.vitalir2.weathervapp.ui.fragments

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.github.vitalir2.weathervapp.R
import io.github.vitalir2.weathervapp.adapters.WeatherForecastAdapter
import io.github.vitalir2.weathervapp.databinding.FragmentSearchCityBinding
import io.github.vitalir2.weathervapp.viewmodels.WeatherForecastViewModel

@AndroidEntryPoint
class SearchWeatherFragment : Fragment() {

    private var _binding: FragmentSearchCityBinding? = null
    private val binding get() = _binding!!
    private val viewModel: WeatherForecastViewModel by viewModels()
    private val adapter = WeatherForecastAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchCityBinding.inflate(inflater, container, false)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSearchView()
        setupRecyclerView()
        observeForecasts()
    }

    private fun setupSearchView() {
        val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        binding.weatherSearch.apply {
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(false)
            isSubmitButtonEnabled = true
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.searchWeatherForecast(query)
                        return true
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }

    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)
        binding.searchRecycler.layoutManager = layoutManager
        binding.searchRecycler.adapter = adapter
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.searchRecycler.addItemDecoration(itemDecoration)
    }

    private fun observeForecasts() {
        viewModel.forecast.observe(viewLifecycleOwner, { list ->
            adapter.submitList(list?.forecasts)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}