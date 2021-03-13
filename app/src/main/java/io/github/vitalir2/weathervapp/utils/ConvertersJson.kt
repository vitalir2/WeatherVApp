package io.github.vitalir2.weathervapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.vitalir2.weathervapp.data.models.Daily
import io.github.vitalir2.weathervapp.data.models.WeatherDescription
import java.lang.reflect.Type

class ConvertersJson {
    private val gson = Gson()
    private val typeDescription: Type = object : TypeToken<List<WeatherDescription>>() {}.type
    private val typeDaily: Type = object : TypeToken<List<Daily>>() {}.type

    @TypeConverter
    fun fromWeatherDescriptionToJson(list: List<WeatherDescription>): String {
        return gson.toJson(list, typeDescription)
    }

    @TypeConverter
    fun fromJsonToWeatherDescription(json: String): List<WeatherDescription> {
        return gson.fromJson(json, typeDescription)
    }

    @TypeConverter
    fun fromDailiesToJson(list: List<Daily>): String {
        return gson.toJson(list, typeDaily)
    }

    @TypeConverter
    fun fromJsonToDailies(json: String): List<Daily> {
        return gson.fromJson(json, typeDaily)
    }
}