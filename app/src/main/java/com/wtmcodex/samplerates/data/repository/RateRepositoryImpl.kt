package com.wtmcodex.samplerates.data.repository

import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.data.local.RateLocalDataSource
import com.wtmcodex.samplerates.data.mapper.toDomain
import com.wtmcodex.samplerates.data.remote.RateRemoteDataSource
import com.wtmcodex.samplerates.di.scopes.IoDispatcher
import com.wtmcodex.samplerates.model.rate.Rate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateRepositoryImpl @Inject constructor(
    private val localDataSource: RateLocalDataSource,
    private val remoteDataSource: RateRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RateRepository {

    override suspend fun getRates(): Result<List<Rate>?> =
        withContext(ioDispatcher) {
            try {
                return@withContext when (val response =
                    remoteDataSource.getRates()) {
                    is Result.Success -> {
                        if (response.data != null) {
                            val result = response.data.toDomain()
                            Result.Success(result)
                        } else {
                            Result.Success(null)
                        }
                    }
                    is Result.Error -> {
                        Result.Error(response.exception)
                    }
                    else -> Result.Success(null)
                }
            } catch (ex: Exception) {
                Result.Error(ex)
            }
        }
}
