package io.github.vitalir2.weathervapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.github.vitalir2.weathervapp.data.models.Daily

@Entity(tableName = "forecasts", primaryKeys = ["lat", "lon"])
data class WeatherForecastEntity(
    val lat: Long,
    val lon: Long,
    val city: String? = null,
    val country: String? = null,

    val dailies: List<Daily>
)