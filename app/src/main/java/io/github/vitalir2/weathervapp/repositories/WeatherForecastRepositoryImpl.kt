package io.github.vitalir2.weathervapp.repositories

import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.data.remote.WeatherForecastRemoteDataSource
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
): WeatherForecastRepository {
    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<List<Daily>?> =
        withContext(Dispatchers.IO) {
            when(val response = weatherForecastRemoteDataSource.getForecastWeather(latitude, longitude)) {
                is Resource.Success -> {
                    if (response.data != null) {
                        Resource.Success(response.data)
                    } else {
                        Resource.Success(null)
                    }
                }
                is Resource.Error -> {
                    Resource.Error(response.message ?: "Error")
                }
                else -> Resource.Loading()
            }
        }
}