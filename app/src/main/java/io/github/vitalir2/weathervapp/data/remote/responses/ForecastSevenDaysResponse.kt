package io.github.vitalir2.weathervapp.data.remote.responses

data class ForecastSevenDaysResponse(
    val daily: List<Daily>,
    val lat: Double,
    val lon: Double,
    val timezone: String,
    val timezone_offset: Int
)