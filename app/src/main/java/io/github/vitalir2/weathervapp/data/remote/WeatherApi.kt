package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.remote.responses.CoordinatesResponse
import io.github.vitalir2.weathervapp.data.remote.responses.WeatherForecastResponse
import io.github.vitalir2.weathervapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/onecall")
    suspend fun getForecastWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("exclude") exclude: String = "current,minutely,hourly,alerts",
        @Query("units") units: String = "metric",
        @Query("lang") language: String = "ru"
    ) : Response<WeatherForecastResponse>

    @GET("geo/1.0/direct")
    suspend fun getCoordinatesByLocation(
        @Query("q") location: String,
        @Query("appid") apiKey: String = API_KEY,
        @Query("limit") limit: Int = 5
    ): Response<CoordinatesResponse>
}