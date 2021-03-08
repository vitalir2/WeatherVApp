package io.github.vitalir2.weathervapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import io.github.vitalir2.weathervapp.utils.ConvertersJson

@Database(entities = [
    WeatherForecastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ConvertersJson::class)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun WeatherForecastDao() : WeatherForecastDao
}