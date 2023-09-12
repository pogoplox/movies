package com.example.moviecompose.di

import com.example.moviecompose.data.api.ApiService
import com.example.moviecompose.data.api.RetrofitFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {
    @Provides
    @Singleton
    fun provideMoviesApi(): ApiService {
        return RetrofitFactory.getBuilder().create(ApiService::class.java)
    }
}