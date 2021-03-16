package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.data.remote.responses.CoordinatesResponse
import io.github.vitalir2.weathervapp.utils.Resource

interface WeatherForecastRemoteDataSource {
    suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherForecast?>

    suspend fun getCoordinatesByLocation(
        location: String
    ): Resource<CoordinatesResponse?>
}