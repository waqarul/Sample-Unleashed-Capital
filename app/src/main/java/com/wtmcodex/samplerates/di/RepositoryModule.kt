package com.wtmcodex.samplerates.di

import com.wtmcodex.samplerates.data.repository.RateRepository
import com.wtmcodex.samplerates.data.repository.RateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRateRepository(repository: RateRepositoryImpl): RateRepository
}