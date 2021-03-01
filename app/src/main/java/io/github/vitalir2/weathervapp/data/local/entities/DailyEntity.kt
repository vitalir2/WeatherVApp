package io.github.vitalir2.weathervapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.vitalir2.weathervapp.data.models.FeelsLike
import io.github.vitalir2.weathervapp.data.models.Temp
import io.github.vitalir2.weathervapp.data.models.WeatherDescription

@Entity(tableName = "dailies")
data class DailyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val forecastId: Long,

    val dt: Int,
    val humidity: Int,
    val pressure: Int,
    val rain: Double,

    @Embedded(prefix = "temp_")
    val temp: Temp,
    @Embedded(prefix = "feels_")
    val feelsLike: FeelsLike,

    val weather: List<WeatherDescription>,

    val wind_deg: Int,
    val wind_speed: Double,

    val clouds: Int,
    val dew_point: Double,
)