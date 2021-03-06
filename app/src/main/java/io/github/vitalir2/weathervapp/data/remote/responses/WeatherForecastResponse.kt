package io.github.vitalir2.weathervapp.data.remote.responses

import com.google.gson.annotations.SerializedName
import io.github.vitalir2.weathervapp.data.models.Daily

data class WeatherForecastResponse(
    val lat: Double,
    val lon: Double,
    val timezone: String,
    @SerializedName("daily")
    val forecasts: List<Daily>,
    )