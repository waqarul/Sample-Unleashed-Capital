package com.wtmcodex.samplerates.data.local

import com.wtmcodex.samplerates.data.local.dao.RateDao
import com.wtmcodex.samplerates.data.local.entity.RateEntity
import com.wtmcodex.samplerates.di.scopes.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RateLocalDataSourceImpl @Inject constructor(
    private val rateDao: RateDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : RateLocalDataSource {
    override suspend fun insertAll(rates: List<RateEntity>) = withContext(ioDispatcher) {
        return@withContext rateDao.insertAll(rates)
    }

    override suspend fun insertOrUpdate(rate: RateEntity) =
        withContext(ioDispatcher) {
            val item = rateDao.getRateBySymbol(rate.symbol)
            if (item == null) {
                rateDao.insertRate(rate)
            } else {
                rateDao.updateRate(rate)
            }
        }

    override suspend fun getAllRates(): List<RateEntity>? =
        withContext(ioDispatcher) {
            return@withContext rateDao.getAllRates()
        }

    override suspend fun getRateBySymbol(symbol: String): RateEntity? =
        withContext(ioDispatcher) {
            return@withContext rateDao.getRateBySymbol(symbol)
        }

    override suspend fun deleteAllRates() = withContext(ioDispatcher) {
        return@withContext rateDao.deleteAllRates()
    }
}