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
    suspend fun insertWeatherForecastList(weatherForecastEntity: List<WeatherForecastEntity>)

    @Query("DELETE FROM forecasts WHERE lat == :latitude AND lon == :longitude")
    suspend fun deleteWeatherForecasts(latitude: Double, longitude: Double)

    @Query("SELECT * FROM forecasts WHERE lat == :latitude AND lon == :longitude")
    fun getWeatherForecasts(latitude: Double, longitude: Double): Flow<List<WeatherForecastEntity>>
}