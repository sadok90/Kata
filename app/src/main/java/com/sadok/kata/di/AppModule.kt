package com.sadok.kata.di

import com.sadok.kata.data.remote.WeatherApi
import com.sadok.kata.data.repository.WeatherRepositoryImpl
import com.sadok.kata.domain.repository.WeatherRepository
import com.sadok.kata.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsApi(): WeatherApi {
        val httpClient = OkHttpClient
            .Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }

}