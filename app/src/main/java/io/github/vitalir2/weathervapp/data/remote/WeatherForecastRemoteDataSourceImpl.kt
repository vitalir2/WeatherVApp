package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.models.WeatherForecast
import io.github.vitalir2.weathervapp.data.remote.responses.CoordinatesResponse
import io.github.vitalir2.weathervapp.utils.Converters
import io.github.vitalir2.weathervapp.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class WeatherForecastRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherForecastRemoteDataSource{

    private val TAG = "WeatherData"

    override suspend fun getForecastWeather(
        latitude: Double,
        longitude: Double
    ): Resource<WeatherForecast?> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = weatherApi.getForecastWeather(latitude, longitude)
                //Log.d(TAG, "GetWeather")
                if (response.isSuccessful) {
                    val converter = Converters()
                    val forecast = response.body()?.let { result ->
                        converter.fromRemoteToModelForecast(result)
                    }
                    Resource.Success(forecast)
                } else {
                   Resource.Success(null)
                }
            } catch (exception: Throwable) {
                when(exception) {
                    is IOException -> Resource.Error("Network Failure")
                    else -> Resource.Error(exception.message ?: "Error")
                }
            }
        }

    override suspend fun getCoordinatesByLocation(location: String): Resource<CoordinatesResponse?> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = weatherApi.getCoordinatesByLocation(location)
                if (response.isSuccessful) {
                    val coordinates = response.body()
                    Resource.Success(coordinates)
                } else {
                    Resource.Success(null)
                }
            } catch (exception: Throwable) {
                when(exception) {
                    is IOException -> Resource.Error("Network Failure")
                    else -> Resource.Error(exception.message ?: "Error")
                }
            }
        }
}