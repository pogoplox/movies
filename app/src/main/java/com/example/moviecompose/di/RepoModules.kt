package com.example.moviecompose.di

import com.example.moviecompose.data.repoimp.MoviesRepositoryImpl
import com.example.moviecompose.domain.repositories.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoModules {

    @Binds
    @Singleton
    abstract fun bindMoviesRepo(repositoryImpl: MoviesRepositoryImpl): MoviesRepository
}