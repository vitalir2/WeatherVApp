package io.github.vitalir2.weathervapp.data.models

data class Daily(
//    val clouds: Int,
    val dt: Int,
    val humidity: Int,
    val pop: Double,
    val pressure: Int,
    val rain: Double,
    val temp: Temp,
    val feelsLike: FeelsLike,
    val weather: List<WeatherDescription>,
    val wind_speed: Double
)