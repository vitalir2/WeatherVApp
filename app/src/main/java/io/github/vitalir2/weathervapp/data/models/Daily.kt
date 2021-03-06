package io.github.vitalir2.weathervapp.data.models

import androidx.room.Embedded

data class Daily(
//    val clouds: Int,
    val dt: Int,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    @Embedded(prefix = "temp_")
    val temp: Temp,
    @Embedded(prefix = "feels_")
    val feelsLike: FeelsLike,
    val weather: List<WeatherDescription>,
    val wind_speed: Double
)