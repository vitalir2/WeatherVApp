package io.github.vitalir2.weathervapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import io.github.vitalir2.weathervapp.data.models.Daily

@Entity(tableName = "forecasts")
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val lat: Double,
    val lon: Double,
    val city: String,
    val country: String,

    @Embedded
    val daily: Daily
)