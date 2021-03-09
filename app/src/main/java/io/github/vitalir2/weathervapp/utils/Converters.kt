package io.github.vitalir2.weathervapp.utils

import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.data.remote.responses.WeatherForecastResponse

fun fromStringDayOfWeekToInt(dayOfWeek: String): Int {
    return when(dayOfWeek) {
        "MONDAY" -> 0
        "TUESDAY" -> 1
        "WEDNESDAY" -> 2
        "THURSDAY" -> 3
        "FRIDAY" -> 4
        "SATURDAY" -> 5
        "SUNDAY" -> 6
        else -> throw IllegalArgumentException()
    }
}

class Converters {

    fun fromRemoteToModelForecast(
        weatherForecastResponse: WeatherForecastResponse
    ): WeatherForecast {
        return WeatherForecast(
            lat = weatherForecastResponse.lat,
            lon = weatherForecastResponse.lon,
            forecasts = weatherForecastResponse.forecasts
        )
    }

}