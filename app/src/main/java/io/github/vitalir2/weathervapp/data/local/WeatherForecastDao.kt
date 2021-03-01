package io.github.vitalir2.weathervapp.data.local

import androidx.room.*
import io.github.vitalir2.weathervapp.data.local.entities.DailyEntity
import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntityProperties
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherProperty(weatherForecastEntityProperties: WeatherForecastEntityProperties): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeatherDailies(vararg dailies: DailyEntity)

    @Transaction
    @Query("SELECT * FROM forecasts")
    fun getForecasts(): Flow<List<WeatherForecastEntity>>
}