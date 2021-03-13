package io.github.vitalir2.weathervapp.data.local

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

interface WeatherForecastLocalDataSource {

    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    suspend fun deleteWeatherForecast(latitude: Long, longitude: Long)

    suspend fun getWeatherForecast(latitude: Long, longitude: Long): WeatherForecastEntity

    suspend fun getRandomForecast(): WeatherForecastEntity
}