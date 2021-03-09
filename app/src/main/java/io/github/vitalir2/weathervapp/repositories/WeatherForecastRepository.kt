package io.github.vitalir2.weathervapp.repositories

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherForecastRepository {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherForecast?>

    suspend fun insertWeatherForecastList(weatherForecastList: List<WeatherForecastEntity>)

    suspend fun deleteWeatherForecasts(latitude: Double, longitude: Double)

    fun getWeatherForecasts(latitude: Double, longitude: Double): Flow<List<WeatherForecastEntity>>
}