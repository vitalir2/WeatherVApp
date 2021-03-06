package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRemoteDataSource {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<List<Daily>?>
}