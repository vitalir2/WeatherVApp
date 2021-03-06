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

    @Query("SELECT * FROM forecasts WHERE city == :city AND country == :country")
    fun getWeatherForecastsByCity(city: String, country: String): Flow<List<WeatherForecastEntity>>
}