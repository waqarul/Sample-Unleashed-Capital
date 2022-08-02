package com.wtmcodex.samplerates.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wtmcodex.samplerates.BuildConfig
import com.wtmcodex.samplerates.R
import com.wtmcodex.samplerates.constants.APIConstants
import com.wtmcodex.samplerates.data.remote.retrofit.RateApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder()
            .readTimeout(APIConstants.READ_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .connectTimeout(APIConstants.CONNECT_TIME_OUT_DELAY, TimeUnit.SECONDS)
            .hostnameVerifier { hostname, session -> true }
            .addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)).build()
    }

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        var level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.NONE
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return HttpLoggingInterceptor().setLevel(level)
    }

    @Singleton
    @Provides
    fun provideRetrofitBuilder(
        client: OkHttpClient,
        @ApplicationContext context: Context
    ): Retrofit {
        val baseApiUrl: String = context.getString(R.string.base_url)
        val baseUrl = String.format("%s/", baseApiUrl)
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRateApiService(retrofit: Retrofit): RateApiService {
        return retrofit.create(RateApiService::class.java)
    }
}