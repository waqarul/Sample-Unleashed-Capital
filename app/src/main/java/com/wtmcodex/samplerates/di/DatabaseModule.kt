package com.wtmcodex.samplerates.di

import android.content.Context
import androidx.room.Room
import com.wtmcodex.samplerates.constants.DatabaseConstants
import com.wtmcodex.samplerates.data.local.RateDatabase
import com.wtmcodex.samplerates.data.local.dao.RateDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): RateDatabase {
        return Room.databaseBuilder(
            context,
            RateDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideRateDao(database: RateDatabase): RateDao {
        return database.rateDao()
    }
}