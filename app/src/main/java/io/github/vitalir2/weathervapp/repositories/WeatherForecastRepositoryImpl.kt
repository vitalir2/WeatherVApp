package io.github.vitalir2.weathervapp.repositories

import android.util.Log
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.data.remote.WeatherForecastRemoteDataSource
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(
    private val weatherForecastRemoteDataSource: WeatherForecastRemoteDataSource
): WeatherForecastRepository {
    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<List<Daily>?> =
        withContext(Dispatchers.IO) {
            when(val response = weatherForecastRemoteDataSource.getForecastWeather(latitude, longitude)) {
                is Resource.Success -> {
                 //   Log.d("Repo", "Success")
                    if (response.data != null) {
                     //   Log.d("Repo", response.data.get(0).temp.day.toString())
                        Resource.Success(response.data)
                    } else {
                     //   Log.d("Repo", "Nothing is here")
                        Resource.Success(null)
                    }
                }
                is Resource.Error -> {
                    Resource.Error(response.message ?: "Error")
                }
                else -> Resource.Loading()
            }
        }
}