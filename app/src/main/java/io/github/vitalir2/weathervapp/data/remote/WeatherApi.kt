package io.github.vitalir2.weathervapp.data.remote

import io.github.vitalir2.weathervapp.data.remote.responses.WeatherForecastResponse
import io.github.vitalir2.weathervapp.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/onecall")
    suspend fun getForecastForSevenDays(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = API_KEY,
        @Query("exclude") exclude: String = "current,minutely,hourly,alerts",
        @Query("lang") language: String = "ru"
    ) : Response<WeatherForecastResponse>
}