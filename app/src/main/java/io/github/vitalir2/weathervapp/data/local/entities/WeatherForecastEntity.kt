package io.github.vitalir2.weathervapp.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "forecasts")
data class WeatherForecastEntityProperties(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val lat: Double,
    val lon: Double,
    val timezone: String
)

data class WeatherForecastEntity(
    @Embedded val forecast: WeatherForecastEntityProperties,
    @Relation(
        parentColumn = "id",
        entityColumn = "forecastId"
    )
    val weatherOnWeek: List<DailyEntity>
)