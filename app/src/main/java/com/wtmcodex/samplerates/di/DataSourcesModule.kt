package com.wtmcodex.samplerates.di

import com.wtmcodex.samplerates.data.local.RateLocalDataSource
import com.wtmcodex.samplerates.data.local.RateLocalDataSourceImpl
import com.wtmcodex.samplerates.data.remote.RateRemoteDataSource
import com.wtmcodex.samplerates.data.remote.RateRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Singleton
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: RateLocalDataSourceImpl): RateLocalDataSource

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RateRemoteDataSourceImpl): RateRemoteDataSource
}