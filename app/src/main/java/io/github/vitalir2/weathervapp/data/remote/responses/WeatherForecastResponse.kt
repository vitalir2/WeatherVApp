package io.github.vitalir2.weathervapp.data.remote.responses

import io.github.vitalir2.weathervapp.data.models.Daily

data class WeatherForecastResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val forecasts: List<Daily>,
    )