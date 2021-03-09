package io.github.vitalir2.weathervapp.data.local

import io.github.vitalir2.weathervapp.data.local.entities.WeatherForecastEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherForecastLocalDataSourceImpl @Inject constructor(
    private val weatherForecastDao: WeatherForecastDao
) : WeatherForecastLocalDataSource {

    override suspend fun insertWeatherForecastList(weatherForecastList: List<WeatherForecastEntity>) {
        weatherForecastDao.insertWeatherForecastList(weatherForecastList)
    }

    override suspend fun deleteWeatherForecasts(latitude: Double, longitude: Double) {
        weatherForecastDao.deleteWeatherForecasts(latitude, longitude)
    }

    override fun getWeatherForecasts(latitude: Double, longitude: Double): Flow<List<WeatherForecastEntity>> {
        return weatherForecastDao.getWeatherForecasts(latitude, longitude)
    }
}