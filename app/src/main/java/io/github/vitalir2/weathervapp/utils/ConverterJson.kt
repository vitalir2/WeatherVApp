package io.github.vitalir2.weathervapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.vitalir2.weathervapp.data.models.WeatherDescription
import java.lang.reflect.Type

class ConverterJson {
    private val gson = Gson()
    private val type: Type = object : TypeToken<List<WeatherDescription>>() {}.type

    @TypeConverter
    fun fromWeatherDescriptionToJson(list: List<WeatherDescription>): String {
        return gson.toJson(list, type)
    }

    @TypeConverter
    fun fromJsonToWeatherDescription(json: String): List<WeatherDescription> {
        return gson.fromJson(json, type)
    }
}