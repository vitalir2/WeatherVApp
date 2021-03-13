package io.github.vitalir2.weathervapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity)

    @Query("DELETE FROM forecasts WHERE lat = :latitude AND lon = :longitude")
    suspend fun deleteWeatherForecast(latitude: Long, longitude: Long)


    @Query("SELECT * FROM forecasts WHERE lat = :latitude AND lon = :longitude LIMIT 1")
    suspend fun getWeatherForecast(latitude: Long, longitude: Long): WeatherForecastEntity

    // Function for testing
    @Query("SELECT * FROM forecasts LIMIT 1")
    suspend fun getRandomForecast(): WeatherForecastEntity
}