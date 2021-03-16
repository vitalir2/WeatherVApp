package io.github.vitalir2.weathervapp.data.remote.responses

data class CoordinatesResponseItem(
    val country: String,
    val name: String,
    val lat: Double,
    val lon: Double,
    val state: String? = null
)