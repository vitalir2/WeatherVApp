package io.github.vitalir2.weathervapp.data.models

data class WeatherForecast(
    val lat: Double,
    val lon: Double,
    val forecasts: List<Daily>
)