package io.github.vitalir2.weathervapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.vitalir2.weathervapp.R
import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.repositories.WeatherForecastRepository
import io.github.vitalir2.weathervapp.utils.Converters
import io.github.vitalir2.weathervapp.utils.Resource
import io.github.vitalir2.weathervapp.utils.isOneWord
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class WeatherForecastViewModel @Inject constructor(
    private val weatherForecastRepository: WeatherForecastRepository
) : ViewModel() {

    private val _forecast = MutableLiveData<WeatherForecast?>()
    val forecast: LiveData<WeatherForecast?> = _forecast

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _weatherGetState = MutableLiveData(true)
    val weatherGetState: LiveData<Boolean> = _weatherGetState

    private val _cityName = MutableLiveData<String>()
    val cityName: LiveData<String>  = _cityName

    private val _weatherSearchTitleRes = MutableLiveData<Int>()
    val weatherSearchTitleRes: LiveData<Int> = _weatherSearchTitleRes

    init {
        _weatherSearchTitleRes.value = R.string.search_result_title
    }

    fun searchWeatherForecast(searchQuery: String) {
        viewModelScope.launch {
            when (val coordinates =
                weatherForecastRepository.getCoordinatesByLocation(searchQuery)) {
                is Resource.Success -> {
                    val result = coordinates.data?.get(0)
                    if (result != null) {
                        getWeatherForecast(result.lat, result.lon)
                        // For localization, else we would get only English city names (result.name)
                        _cityName.postValue(getMainLocationName(searchQuery))
                    }
                }
                is Resource.Error -> {
                    coordinates.message?.let { Timber.d("No connection or something else") }
                }
            }
        }
    }

    private fun getMainLocationName(location: String): String {
        return if (location.isOneWord()) {
            location
        } else {
            location.dropLastWhile { character ->
                character != ',' && character != ' '
            }
        }
    }

    private fun getWeatherForecast(latitude: Double, longitude: Double) {
        _isLoading.value = true
        viewModelScope.launch {
            when (val result =
                weatherForecastRepository.getForecastWeather(latitude, longitude)) {
                is Resource.Success -> {
                    _isLoading.postValue(false)
                    if (result.data != null) {
                        _weatherGetState.postValue(true)
                        _forecast.value = result.data
                        forecast.value?.let { forecast ->
                            val converter = Converters()
                            weatherForecastRepository.insertWeatherForecast(
                                converter.fromModelToDatabaseForecast(forecast)
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    Timber.d("No connection or something else")
                    result.message?.let { Timber.d(it) }
                    getWeatherForecastFromLocal(latitude, longitude)
                }
                is Resource.Loading -> {
                    _isLoading.postValue(true)
                }
            }
        }
    }

    private fun getWeatherForecastFromLocal(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            Timber.d("Now I'm in database, lat=$latitude, lon=$longitude")
            _isLoading.postValue(true)
            try {
                val result = weatherForecastRepository.getWeatherForecast(
                    latitude,
                    longitude)
                _forecast.postValue(result)
                _isLoading.postValue(false)
                _weatherGetState.postValue(true)
            } catch (exception: Throwable) {
                Timber.d("Error: ${exception.message}")
                _isLoading.postValue(false )
                _weatherGetState.postValue(false)
            }
        }
    }
}