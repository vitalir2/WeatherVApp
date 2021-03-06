package io.github.vitalir2.weathervapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.vitalir2.weathervapp.data.remote.WeatherForecastRemoteDataSource
import io.github.vitalir2.weathervapp.data.remote.WeatherForecastRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun provideWeatherForecastRemoteDataSource(
        weatherForecastRemoteDataSourceImpl: WeatherForecastRemoteDataSourceImpl
    ): WeatherForecastRemoteDataSource
}