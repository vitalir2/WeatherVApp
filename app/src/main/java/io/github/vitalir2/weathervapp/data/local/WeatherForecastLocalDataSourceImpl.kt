package io.github.vitalir2.weathervapp.data.local

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import javax.inject.Inject

class WeatherForecastLocalDataSourceImpl @Inject constructor(
    private val weatherForecastDao: WeatherForecastDao
) : WeatherForecastLocalDataSource {

    override suspend fun insertWeatherForecast(weatherForecastEntity: WeatherForecastEntity) {
        weatherForecastDao.insertWeatherForecast(weatherForecastEntity)
    }

    override suspend fun deleteWeatherForecast(latitude: Long, longitude: Long) {
        weatherForecastDao.deleteWeatherForecast(latitude, longitude)
    }

    override suspend fun getWeatherForecast(latitude: Long, longitude: Long): WeatherForecastEntity {
        return weatherForecastDao.getWeatherForecast(latitude, longitude)
    }

    override suspend fun getRandomForecast(): WeatherForecastEntity {
        return weatherForecastDao.getRandomForecast()
    }
}