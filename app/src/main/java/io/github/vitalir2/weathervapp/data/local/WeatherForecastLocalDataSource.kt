package io.github.vitalir2.weathervapp.data.local

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

interface WeatherForecastLocalDataSource {

    suspend fun insertWeatherForecastList(weatherForecastList: List<WeatherForecastEntity>)

    suspend fun deleteWeatherForecasts(latitude: Double, longitude: Double)

    fun getWeatherForecasts(latitude: Double, longitude: Double): Flow<List<WeatherForecastEntity>>
}