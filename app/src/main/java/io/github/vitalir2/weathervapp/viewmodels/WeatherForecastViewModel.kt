package io.github.vitalir2.weathervapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.repositories.WeatherForecastRepository
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _forecasts = MutableLiveData<List<Daily>?>()
    val forecasts: LiveData<List<Daily>?> = _forecasts

    fun getWeatherForecast(latitude: Double, longitude: Double) {
        viewModelScope.launch {
          //  Log.d("ViewModel", "In view Model")
            when (val result = weatherForecastRepository.getForecastWeather(latitude, longitude)) {
                is Resource.Success -> {
                    if (!result.data.isNullOrEmpty()) {
                        _forecasts.value = result.data
                    //    Log.d("ViewModel", "GetData")
                    }
                }
            }
        }
    }
}