package com.wtmcodex.samplerates.data.local

import com.wtmcodex.samplerates.data.local.entity.RateEntity

interface RateLocalDataSource {
    suspend fun insertAll(rates: List<RateEntity>)
    suspend fun insertOrUpdate(rate: RateEntity)
    suspend fun getAllRates(): List<RateEntity>?
    suspend fun getRateBySymbol(symbol: String): RateEntity?
    suspend fun deleteAllRates()
}