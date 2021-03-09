package io.github.vitalir2.weathervapp.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.vitalir2.weathervapp.data.local.WeatherDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room
            .databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherForecastDao(db: WeatherDatabase) = db.WeatherForecastDao()
}