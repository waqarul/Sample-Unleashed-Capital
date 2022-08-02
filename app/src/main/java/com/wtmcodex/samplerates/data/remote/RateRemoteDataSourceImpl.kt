package com.wtmcodex.samplerates.data.remote

import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.data.remote.retrofit.RateApiService
import com.wtmcodex.samplerates.di.scopes.IoDispatcher
import com.wtmcodex.samplerates.model.network.RateDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateRemoteDataSourceImpl @Inject constructor(
    private val apiService: RateApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    RateRemoteDataSource {
    override suspend fun getRates(): Result<List<RateDTO>?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getRates()
                if (result.isSuccessful) {
                    val data = result.body()?.data
                    Result.Success(data)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }
}