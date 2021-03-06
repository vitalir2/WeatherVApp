package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class WeatherForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherForecastRemoteDataSource{
    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<List<Daily>?> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = weatherApi.getForecastWeather(latitude, longitude)
                if (response.isSuccessful) {
                    response.body()?.forecasts.let { result ->
                        Resource.Success(result)
                    }
                } else {
                    Resource.Success(null)
                }
            } catch (exception: Exception) {
                when(exception) {
                    is IOException -> Resource.Error("Network Failure")
                    else -> Resource.Error("Error")
                }
            }
        }
}