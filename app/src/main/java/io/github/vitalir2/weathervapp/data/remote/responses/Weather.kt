package io.github.vitalir2.weathervapp.data.remote.responses

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)