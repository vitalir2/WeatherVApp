package io.github.vitalir2.weathervapp.repositories

import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.utils.Resource

interface WeatherForecastRepository {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<List<Daily>?>
}