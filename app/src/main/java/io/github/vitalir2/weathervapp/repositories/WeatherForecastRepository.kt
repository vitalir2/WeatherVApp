package io.github.vitalir2.weathervapp.repositories

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.data.remote.responses.CoordinatesResponse
import io.github.vitalir2.weathervapp.utils.Resource

interface WeatherForecastRepository {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherForecast?>

    suspend fun getCoordinatesByLocation(query: String): Resource<CoordinatesResponse?>

    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    suspend fun deleteWeatherForecast(latitude: Double, longitude: Double)

    suspend fun getWeatherForecast(latitude: Double, longitude: Double): WeatherForecast

    suspend fun getRandomForecast(): WeatherForecast
}